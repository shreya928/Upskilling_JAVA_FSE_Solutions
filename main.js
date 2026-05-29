// =============================================================
// main.js — Local Community Event Portal
// JavaScript Exercises: All 14 tasks covered
// =============================================================

// =============================================================
// Exercise JS1: Setup — log welcome message
// =============================================================
console.log("Welcome to the Community Portal");
console.log("main.js loaded successfully ✅");

// =============================================================
// Exercise JS2: Syntax, Data Types, Operators
// =============================================================
const portalName = "CityConnect Community Portal"; // const for fixed value
const launchDate = "2025-01-01";                    // const for fixed date
let availableSeats = 150;                           // let for mutable value

// Template literal to show event info
const eventInfo = `${portalName} launched on ${launchDate}. Seats available: ${availableSeats}`;
console.log("Event Info:", eventInfo);

// Increment/decrement seat count
function incrementSeat() { availableSeats++; }
function decrementSeat() { availableSeats--; }

// =============================================================
// Exercise JS2 + JS5: Event Objects — Constructor / Class
// =============================================================
class Event {
  constructor(id, name, date, category, fee, seats) {
    this.id = id;
    this.name = name;
    this.date = new Date(date);
    this.category = category;
    this.fee = fee;
    this.seats = seats;
  }
}

// Exercise JS5: Add method to prototype
Event.prototype.checkAvailability = function () {
  return this.seats > 0;
};

// =============================================================
// Exercise JS6: Arrays — Event data
// =============================================================
let eventsArray = [
  new Event(1, "Music Concert",       "2025-07-15", "music",    "₹200", 200),
  new Event(2, "Baking Workshop",     "2025-07-20", "workshop", "₹150", 30),
  new Event(3, "City Sports Day",     "2025-08-01", "sports",   "Free", 500),
  new Event(4, "Cultural Night",      "2025-08-10", "cultural", "₹100", 0),
  new Event(5, "Photography Workshop","2025-09-05", "workshop", "₹200", 20),
  new Event(6, "Jazz Evening",        "2025-09-15", "music",    "₹250", 80),
];

// Exercise JS5: Log keys and values using Object.entries
console.log("First event details:");
Object.entries(eventsArray[0]).forEach(([key, value]) => {
  console.log(`  ${key}:`, value);
});

// =============================================================
// Exercise JS4: Closures — track registrations per category
// =============================================================
function createCategoryTracker() {
  const registrationCount = {}; // closure variable
  return {
    register: function (category) {
      registrationCount[category] = (registrationCount[category] || 0) + 1;
      console.log(`Registrations for "${category}": ${registrationCount[category]}`);
    },
    getCount: function (category) {
      return registrationCount[category] || 0;
    },
    getAll: function () {
      return { ...registrationCount };
    }
  };
}
const tracker = createCategoryTracker(); // closure instance

// =============================================================
// Exercise JS3: Conditionals + Loops — render events
// Exercise JS7: DOM Manipulation
// =============================================================
function renderEvents(eventsToRender) {
  const eventList = document.getElementById("eventList");
  if (!eventList) return;

  eventList.innerHTML = ""; // clear existing
  const today = new Date();

  // Exercise JS3: Loop with forEach
  eventsToRender.forEach(evt => {
    // Exercise JS3: if-else — skip past or full events from display but still show with label
    const isPast = evt.date < today;
    const isFull = evt.seats === 0;

    // Exercise JS6: .map() formatting — "Workshop on Baking" style
    const displayName = evt.category === "workshop"
      ? `Workshop on ${evt.name.replace("Workshop", "").trim()}`
      : evt.name;

    // Exercise JS7: createElement and append
    const card = document.createElement("div");
    card.classList.add("eventCard");
    card.dataset.category = evt.category;
    card.dataset.id = evt.id;

    card.innerHTML = `
      <h3>${displayName}</h3>
      <p class="event-meta">📅 ${evt.date.toDateString()}</p>
      <p class="event-meta">🏷️ ${evt.category.charAt(0).toUpperCase() + evt.category.slice(1)}</p>
      <p class="event-meta">💺 Seats: ${isFull ? '<span style="color:red">Full</span>' : evt.seats}</p>
      <p class="event-fee">${evt.fee}</p>
      ${isPast ? '<p style="color:#999;font-size:0.85rem">⚠️ Event has passed</p>' : ""}
      ${isFull ? '<p style="color:red;font-size:0.85rem">❌ No seats available</p>' : ""}
      <button class="cta-button" 
        onclick="registerUser(${evt.id})" 
        ${isPast || isFull ? 'disabled style="opacity:0.5;cursor:not-allowed"' : ""}>
        Register
      </button>
    `;

    eventList.appendChild(card);
  });
}

// =============================================================
// Exercise JS4: Higher-Order Function — filterEventsByCategory
// Exercise JS6: .filter() for music events
// =============================================================
function filterEventsByCategory(category) {
  let filtered;
  if (category === "all") {
    // Exercise JS10: Spread operator to clone before filtering
    filtered = [...eventsArray];
  } else {
    // Exercise JS6: .filter() — show only selected category
    filtered = eventsArray.filter(evt => evt.category === category);
  }
  renderEvents(filtered);
}

// =============================================================
// Exercise JS4: addEvent function
// Exercise JS6: .push()
// =============================================================
function addEvent(name, date, category, fee, seats) {
  const newId = eventsArray.length + 1;
  const newEvent = new Event(newId, name, date, category, fee, seats);
  eventsArray.push(newEvent); // Exercise JS6: push to array
  renderEvents(eventsArray);
  console.log(`New event added: ${name}`);
}

// =============================================================
// Exercise JS4: registerUser function with closure tracker
// Exercise JS7: Update UI on register
// =============================================================
function registerUser(eventId) {
  // Exercise JS3: try-catch error handling
  try {
    const evt = eventsArray.find(e => e.id === eventId);
    if (!evt) throw new Error("Event not found");
    if (!evt.checkAvailability()) throw new Error("No seats available");
    if (evt.date < new Date()) throw new Error("Event has already passed");

    evt.seats--;
    decrementSeat();
    tracker.register(evt.category); // closure tracker

    // Exercise JS7: Update UI
    alert(`✅ Successfully registered for: ${evt.name}\nSeats remaining: ${evt.seats}`);
    renderEvents([...eventsArray]);

  } catch (error) {
    // Exercise JS3: catch block
    alert(`❌ Registration failed: ${error.message}`);
    console.error("Registration error:", error.message);
  }
}

// =============================================================
// Exercise JS8: keydown — quick search
// =============================================================
function quickSearch(event) {
  const query = event.target.value.toLowerCase();
  const filtered = eventsArray.filter(evt =>
    evt.name.toLowerCase().includes(query)
  );
  renderEvents(filtered);
}

// =============================================================
// Exercise HTML5/JS11: Form Handling
// =============================================================
function handleFormSubmit(event) {
  // Exercise JS11: Prevent default behavior
  event.preventDefault();

  const form = document.getElementById("registrationForm");
  const name = form.elements["fullName"].value.trim();
  const email = form.elements["email"].value.trim();
  const eventDate = form.elements["eventDate"].value;
  const eventType = form.elements["eventType"].value;

  // Exercise JS11: Inline validation
  if (!name || !email || !eventDate || !eventType) {
    alert("Please fill in all required fields.");
    return;
  }

  // Exercise JS12: Simulate AJAX POST with fetch
  submitRegistration({ name, email, eventDate, eventType });
}

// =============================================================
// Exercise JS12: AJAX & Fetch API — POST registration
// Exercise JS9: async/await + loading spinner
// =============================================================
async function submitRegistration(data) {
  const output = document.getElementById("formOutput");
  output.style.display = "block";
  output.textContent = "⏳ Submitting your registration...";

  try {
    // Exercise JS9: async/await
    // Using JSONPlaceholder as mock API
    const response = await fetch("https://jsonplaceholder.typicode.com/posts", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    });

    if (!response.ok) throw new Error("Server error");

    const result = await response.json();
    console.log("API Response:", result);

    // Exercise JS12: Show success
    // Exercise JS12: setTimeout to simulate delay
    setTimeout(() => {
      output.style.display = "block";
      output.textContent = `✅ Registration confirmed for ${data.name} at ${data.eventType} event! (Ref: #${result.id})`;
      document.getElementById("registrationForm").reset();
    }, 1000);

  } catch (error) {
    // Exercise JS9: .catch() equivalent
    output.style.background = "#fdecea";
    output.style.borderColor = "#f5c6cb";
    output.style.color = "#c62828";
    output.textContent = `❌ Submission failed: ${error.message}`;
    console.error("Fetch error:", error);
  }
}

// =============================================================
// Exercise HTML5 Exercise 5: <output> confirmation display
// Exercise HTML6: onclick confirmation
// =============================================================
function showConfirmation() {
  const name = document.getElementById("fullName").value;
  if (name) {
    console.log(`Form submitted by: ${name}`);
  }
}

// =============================================================
// Exercise HTML6: onchange — show event fee
// =============================================================
const eventFees = {
  music: "₹200",
  workshop: "₹150",
  sports: "Free",
  cultural: "₹100"
};

function showEventFee(value) {
  const feeDisplay = document.getElementById("feeDisplay");
  if (value && eventFees[value]) {
    feeDisplay.textContent = `💰 Event Fee: ${eventFees[value]}`;
  } else {
    feeDisplay.textContent = "";
  }
}

// =============================================================
// Exercise HTML6: onblur — phone validation
// =============================================================
function validatePhone(value) {
  const phoneError = document.getElementById("phoneError");
  const phoneRegex = /^[6-9]\d{9}$/;
  if (value && !phoneRegex.test(value)) {
    phoneError.textContent = "⚠️ Enter a valid 10-digit Indian phone number.";
  } else {
    phoneError.textContent = "";
  }
}

// =============================================================
// Exercise HTML6: ondblclick — enlarge image (lightbox)
// =============================================================
function enlargeImage(imgElement) {
  const lightbox = document.getElementById("lightbox");
  const lightboxImg = document.getElementById("lightboxImg");
  lightboxImg.src = imgElement.src;
  lightboxImg.alt = imgElement.alt;
  lightbox.classList.add("active");
}

function closeLightbox() {
  document.getElementById("lightbox").classList.remove("active");
}

// =============================================================
// Exercise HTML6: character count in feedback textarea
// =============================================================
function countChars(textarea) {
  const count = textarea.value.length;
  const max = 500;
  const display = document.getElementById("charCount");
  if (count > max) {
    textarea.value = textarea.value.substring(0, max);
    display.style.color = "red";
  } else {
    display.style.color = count > 450 ? "orange" : "#888";
  }
  display.textContent = `${Math.min(count, max)} / ${max} characters`;
}

function submitFeedback() {
  const text = document.getElementById("feedbackText").value.trim();
  if (!text) {
    alert("Please write your feedback before submitting.");
    return;
  }
  alert(`✅ Thank you for your feedback!\n"${text.substring(0, 60)}${text.length > 60 ? "..." : ""}"`);
  document.getElementById("feedbackText").value = "";
  document.getElementById("charCount").textContent = "0 / 500 characters";
}

// =============================================================
// Exercise HTML7: Video — oncanplay event
// =============================================================
function videoReady() {
  const status = document.getElementById("videoStatus");
  if (status) {
    status.textContent = "✅ Video ready to play!";
    status.style.color = "#2e7d32";
  }
  console.log("Video is ready to play.");
}

// =============================================================
// Exercise HTML8: localStorage — save/retrieve event preference
// =============================================================
function savePreference(eventType) {
  localStorage.setItem("preferredEventType", eventType);
  console.log(`Preference saved: ${eventType}`);
}

function loadPreference() {
  const saved = localStorage.getItem("preferredEventType");
  const select = document.getElementById("eventType");
  if (saved && select) {
    select.value = saved;
    showEventFee(saved); // update fee display
    console.log(`Loaded preference: ${saved}`);
  }
}

// Exercise HTML8: Clear both storages
function clearPreferences() {
  localStorage.clear();
  sessionStorage.clear();
  const select = document.getElementById("eventType");
  if (select) select.value = "";
  document.getElementById("feeDisplay").textContent = "";
  alert("✅ Preferences cleared!");
  console.log("localStorage and sessionStorage cleared.");
}

// =============================================================
// Exercise HTML9: Geolocation
// =============================================================
function findNearbyEvents() {
  const result = document.getElementById("geoResult");
  result.style.display = "block";
  result.textContent = "📍 Fetching your location...";

  if (!navigator.geolocation) {
    result.textContent = "❌ Geolocation is not supported by your browser.";
    return;
  }

  // Exercise HTML9: High accuracy options
  const options = {
    enableHighAccuracy: true,
    timeout: 10000,
    maximumAge: 0
  };

  navigator.geolocation.getCurrentPosition(
    // Exercise HTML9: Success callback
    function (position) {
      const lat = position.coords.latitude.toFixed(4);
      const lon = position.coords.longitude.toFixed(4);
      const accuracy = position.coords.accuracy.toFixed(1);
      result.innerHTML = `
        <strong>📍 Location Found!</strong><br>
        Latitude: ${lat} | Longitude: ${lon}<br>
        Accuracy: ±${accuracy} meters<br>
        <em>Showing events near your location...</em>
      `;
      console.log(`Location: ${lat}, ${lon}`);
    },
    // Exercise HTML9: Error handling
    function (error) {
      let msg = "";
      switch (error.code) {
        case error.PERMISSION_DENIED:
          msg = "❌ Location permission denied. Please allow location access.";
          break;
        case error.POSITION_UNAVAILABLE:
          msg = "❌ Location information unavailable.";
          break;
        case error.TIMEOUT:
          msg = "⏱️ Request timed out. Please try again.";
          break;
        default:
          msg = "❌ An unknown error occurred.";
      }
      result.textContent = msg;
      console.error("Geolocation error:", msg);
    },
    options
  );
}

// =============================================================
// Exercise JS9: Fetch events from mock JSON (Promises style)
// =============================================================
function fetchEventsFromAPI() {
  console.log("Fetching events from API...");

  fetch("https://jsonplaceholder.typicode.com/todos?_limit=3")
    .then(response => {
      if (!response.ok) throw new Error("Network response was not ok");
      return response.json();
    })
    .then(data => {
      console.log("Events fetched via .then():", data);
    })
    .catch(error => {
      console.error("Fetch failed:", error);
    });
}

// Exercise JS9: Same fetch rewritten with async/await
async function fetchEventsAsync() {
  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/todos?_limit=3");
    if (!response.ok) throw new Error("Network error");
    const data = await response.json();
    console.log("Events fetched via async/await:", data);
  } catch (err) {
    console.error("Async fetch error:", err);
  }
}

// =============================================================
// Exercise JS10: Destructuring — extract event details
// =============================================================
function showEventDetails(eventId) {
  const evt = eventsArray.find(e => e.id === eventId);
  if (!evt) return;

  // Destructuring to extract event details
  const { name, category, fee, seats } = evt;
  console.log(`Event: ${name}, Category: ${category}, Fee: ${fee}, Seats: ${seats}`);
}

// =============================================================
// Exercise JS10: Default parameters
// =============================================================
function createEventCard(name, category = "general", fee = "Free", seats = 50) {
  return `${name} | ${category} | ${fee} | ${seats} seats`;
}

// =============================================================
// Exercise JS13: Debugging — log form submission steps
// =============================================================
function debugFormSubmit() {
  console.group("Form Submission Debug");
  console.log("Step 1: Form submit triggered");
  const name = document.getElementById("fullName")?.value;
  const email = document.getElementById("email")?.value;
  console.log("Step 2: Name =", name);
  console.log("Step 3: Email =", email);
  console.log("Step 4: Validation running...");
  console.log("Step 5: Fetch POST being called...");
  console.groupEnd();
}

// =============================================================
// PAGE INIT — Run on load
// =============================================================
document.addEventListener("DOMContentLoaded", function () {
  console.log("DOM fully loaded ✅");

  // Render initial events
  renderEvents(eventsArray);

  // Load saved preference from localStorage
  loadPreference();

  // Fetch events (demonstrate both promise styles)
  fetchEventsFromAPI();
  fetchEventsAsync();

  // Log event details using destructuring
  showEventDetails(1);

  // Log default parameter function
  console.log("Sample card:", createEventCard("Yoga Session"));

  // Exercise JS13: Debug log
  debugFormSubmit();

  // Exercise JS10: Spread + filter example
  const musicEvents = [...eventsArray].filter(e => e.category === "music");
  console.log("Music events (spread + filter):", musicEvents.map(e => e.name));
});