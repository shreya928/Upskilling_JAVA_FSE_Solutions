USE event_portal;

-- Q1. User Upcoming Events
SELECT u.full_name, e.title, e.city, e.start_date
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.status = 'upcoming' AND e.city = u.city
ORDER BY e.start_date;

-- Q2. Top Rated Events
SELECT e.title, AVG(f.rating) AS avg_rating, COUNT(f.feedback_id) AS total_feedback
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
ORDER BY avg_rating DESC;

-- Q3. Inactive Users
SELECT u.user_id, u.full_name, u.email
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
  AND r.registration_date >= CURDATE() - INTERVAL 90 DAY
WHERE r.registration_id IS NULL;

-- Q4. Peak Session Hours
SELECT e.title, COUNT(s.session_id) AS peak_sessions
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
WHERE TIME(s.start_time) BETWEEN '10:00:00' AND '12:00:00'
GROUP BY e.event_id, e.title;

-- Q5. Most Active Cities
SELECT u.city, COUNT(DISTINCT r.user_id) AS registered_users
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY registered_users DESC
LIMIT 5;

-- Q6. Event Resource Summary
SELECT e.title,
  SUM(IF(r.resource_type = 'pdf',   1, 0)) AS pdf_count,
  SUM(IF(r.resource_type = 'image', 1, 0)) AS image_count,
  SUM(IF(r.resource_type = 'link',  1, 0)) AS link_count,
  COUNT(r.resource_id) AS total_resources
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title;

-- Q7. Low Feedback Alerts
SELECT u.full_name, e.title AS event_title, f.rating, f.comments, f.feedback_date
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3;

-- Q8. Sessions per Upcoming Event
SELECT e.title, e.start_date, COUNT(s.session_id) AS session_count
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id, e.title, e.start_date;

-- Q9. Organizer Event Summary
SELECT u.full_name AS organizer_name, e.status, COUNT(e.event_id) AS event_count
FROM Users u
JOIN Events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, u.full_name, e.status
ORDER BY u.full_name, e.status;

-- Q10. Feedback Gap
SELECT e.event_id, e.title, COUNT(r.registration_id) AS total_registrations
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE f.feedback_id IS NULL
GROUP BY e.event_id, e.title;

-- Q11. Daily New User Count
SELECT registration_date, COUNT(user_id) AS new_users
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY registration_date
ORDER BY registration_date;

-- Q12. Event with Maximum Sessions
SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(s.session_id) = (
  SELECT MAX(cnt) FROM (
    SELECT COUNT(session_id) AS cnt FROM Sessions GROUP BY event_id
  ) AS sub
);

-- Q13. Average Rating per City
SELECT e.city, ROUND(AVG(f.rating), 2) AS avg_rating, COUNT(f.feedback_id) AS feedback_count
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.city
ORDER BY avg_rating DESC;

-- Q14. Most Registered Events
SELECT e.title, COUNT(r.registration_id) AS total_registrations
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title
ORDER BY total_registrations DESC
LIMIT 3;

-- Q15. Event Session Time Conflict
SELECT a.event_id, a.title AS session_a, b.title AS session_b,
  a.start_time AS a_start, a.end_time AS a_end,
  b.start_time AS b_start, b.end_time AS b_end
FROM Sessions a
JOIN Sessions b ON a.event_id = b.event_id
  AND a.session_id < b.session_id
  AND a.start_time < b.end_time
  AND a.end_time > b.start_time;

-- Q16. Unregistered Active Users
SELECT u.user_id, u.full_name, u.email, u.registration_date
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
WHERE u.registration_date >= CURDATE() - INTERVAL 30 DAY
  AND r.registration_id IS NULL;

-- Q17. Multi-Session Speakers
SELECT speaker_name, COUNT(session_id) AS total_sessions
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(session_id) > 1
ORDER BY total_sessions DESC;

-- Q18. Resource Availability Check
SELECT e.event_id, e.title, e.status
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
WHERE r.resource_id IS NULL;

-- Q19. Completed Events with Feedback Summary
SELECT e.title,
  COUNT(DISTINCT r.registration_id) AS total_registrations,
  ROUND(AVG(f.rating), 2) AS avg_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id, e.title;

-- Q20. User Engagement Index
SELECT u.user_id, u.full_name,
  COUNT(DISTINCT r.event_id) AS events_attended,
  COUNT(DISTINCT f.feedback_id) AS feedbacks_submitted
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY events_attended DESC;

-- Q21. Top Feedback Providers
SELECT u.full_name, COUNT(f.feedback_id) AS feedback_count
FROM Users u
JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY feedback_count DESC
LIMIT 5;

-- Q22. Duplicate Registrations Check
SELECT user_id, event_id, COUNT(registration_id) AS registration_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(registration_id) > 1;

-- Q23. Registration Trends
SELECT DATE_FORMAT(registration_date, '%Y-%m') AS month,
  COUNT(registration_id) AS registrations
FROM Registrations
WHERE registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY DATE_FORMAT(registration_date, '%Y-%m')
ORDER BY month;

-- Q24. Average Session Duration per Event
SELECT e.title,
  ROUND(AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)), 2) AS avg_duration_minutes
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title;

-- Q25. Events Without Sessions
SELECT e.event_id, e.title, e.status, e.start_date
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE s.session_id IS NULL;
