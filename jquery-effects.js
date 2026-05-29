// =============================================================
// jquery-effects.js — Exercise JS14: jQuery and JS Frameworks
// Requires jQuery (loaded from CDN in index.html)
// =============================================================

$(document).ready(function () {
  console.log("jQuery loaded ✅ Version:", $.fn.jquery);

  // -------------------------------------------------------
  // Exercise JS14: $('#registerBtn').click(...)
  // -------------------------------------------------------
  $("#registerBtn").click(function () {
    console.log("Register button clicked (jQuery handler)");
    // Visual pulse on click via jQuery
    $(this).animate({ opacity: 0.5 }, 150, function () {
      $(this).animate({ opacity: 1 }, 150);
    });
  });

  // -------------------------------------------------------
  // Exercise JS14: .fadeIn() and .fadeOut() for event cards
  // -------------------------------------------------------

  // Fade in all event cards on load
  // (cards are created dynamically, so we delegate with a slight delay)
  setTimeout(function () {
    $(".eventCard").hide().each(function (index) {
      $(this).delay(index * 100).fadeIn(400);
    });
  }, 300);

  // Fade out then fade in when filter changes (jQuery version)
  $("#categoryFilter").on("change", function () {
    $(".eventCard").fadeOut(200, function () {
      // Re-render handled by main.js; jQuery just re-fades in
      setTimeout(function () {
        $(".eventCard").fadeIn(300);
      }, 250);
    });
  });

  // -------------------------------------------------------
  // Bonus: jQuery hover effect on gallery images
  // -------------------------------------------------------
  $(document).on("mouseenter", ".galleryImg", function () {
    $(this).stop().animate({ opacity: 0.8 }, 200);
  }).on("mouseleave", ".galleryImg", function () {
    $(this).stop().animate({ opacity: 1 }, 200);
  });

  console.log(
    "💡 jQuery Benefit: Simplifies DOM manipulation, events, and animations " +
    "with cross-browser compatible, chainable syntax.\n" +
    "Modern frameworks like React or Vue go further — they use a virtual DOM, " +
    "component-based architecture, and reactive state management, making large " +
    "applications easier to scale and maintain compared to jQuery."
  );
});

/*
  Exercise JS14 — Framework Note (printed to console above):
  ─────────────────────────────────────────────────────────
  jQuery simplifies tasks like DOM selection ($), event binding (.click, .on),
  animations (.fadeIn, .fadeOut), and AJAX (.ajax, $.get) with less code
  than vanilla JS and consistent cross-browser behavior.

  React / Vue Benefits over jQuery:
  - Component-based: UI is split into reusable, self-contained pieces.
  - Reactive state: UI automatically re-renders when data changes.
  - Virtual DOM (React): Efficient updates without manual DOM manipulation.
  - Vue: Two-way data binding with v-model simplifies forms.
  - Both scale better for large SPAs (Single Page Applications).
*/