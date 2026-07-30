/* =========================================
   Theme switcher
   ========================================= */
function setTheme(themeName) {
    // Applies the data-theme attribute to the root <html> tag
    document.documentElement.setAttribute('data-theme', themeName);
    
    // Saves the choice to browser memory so it persists across pages
    localStorage.setItem('portfolioTheme', themeName);
}

// Check for saved theme on initial page load
let savedTheme = localStorage.getItem('portfolioTheme');
if (savedTheme) {
    setTheme(savedTheme);
}

/* =========================================
   Interactive Animations, Effects & Form Logic
   ========================================= */
// This runs the exact second the HTML finishes loading
document.addEventListener("DOMContentLoaded", () => {
    
    /* =========================================
       1. The Boot-Up Sequence
       ========================================= */
    // Find every single Windows 95 box on the screen
    const boxes = document.querySelectorAll('.win95-box');
    
    // Hide them all instantly and push them down slightly
    boxes.forEach(box => {
        box.style.opacity = '0';
        box.style.transform = 'translateY(10px)'; 
        box.style.transition = 'opacity 0.1s, transform 0.1s'; // Fast, snappy transition
    });

    // Make them appear one by one with a slight delay (like an old CPU loading)
    boxes.forEach((box, index) => {
        setTimeout(() => {
            box.style.opacity = '1';
            box.style.transform = 'translateY(0)';
        }, index * 150); // Multiplies the delay by the box number (0ms, 150ms, 300ms, etc.)
    });

    /* =========================================
       2. Glitch Effect
       ========================================= */
    // Find all the artwork images
    const artworks = document.querySelectorAll('.artwork-card img');
    
    artworks.forEach(art => {
        art.addEventListener('mouseover', () => {
            // Apply a crazy color filter and shift the image randomly
            art.style.filter = `contrast(200%) hue-rotate(${Math.random() * 90}deg)`;
            art.style.transform = `translate(${Math.random() * 6 - 3}px, ${Math.random() * 6 - 3}px)`;
            
            // Snap it back to normal after a tiny fraction of a second (100ms)
            setTimeout(() => {
                art.style.filter = 'none';
                art.style.transform = 'none';
            }, 100); 
        });
    });

    /* =========================================
       3. Database Form Submission (AJAX & Merge)
       ========================================= */
    // We can use jQuery here safely since it is loaded in your JSP's <head>
    $("#searchForm").on("submit", function(event) {
        event.preventDefault(); // Prevents the page from reloading on submit

        let searchInput = $("input[name='artistName']").val();

        // AJAX call to fetch data without leaving the page
        $.ajax({
            type: "POST",
            url: "your-server-endpoint", // Replace with your actual Java servlet URL
            data: { artistName: searchInput },
            success: function(newData) {
                // Example of merging arrays using jQuery
                let existingArtwork = ["Project 1", "Project 2"]; 
                let fetchedArtwork = newData.results || []; // Assuming the server returns a JSON array
                
                // Merging the new data into the existing array
                let mergedArtwork = $.merge(existingArtwork, fetchedArtwork);
                
                console.log("Merged Terminal Data:", mergedArtwork);
                
                // You would then write logic here to inject the merged data into your HTML
            },
            error: function() {
                console.error("CONNECTION FAILED. CHECK NETWORK.");
            }
        });
    });
});