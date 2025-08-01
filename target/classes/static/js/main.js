/**
 * Main JavaScript for the Rubik's Cube Solver application
 */

// Wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Initialize page transitions
    initPageTransitions();
    
    // Initialize any interactive components
    initComponents();
});

/**
 * Initialize page transitions
 */
function initPageTransitions() {
    // Add fade-in class to the page content
    const pageContent = document.querySelector('main');
    if (pageContent) {
        pageContent.classList.add('page-transition');
        setTimeout(() => {
            pageContent.classList.add('fade-in');
        }, 100);
    }
    
    // Add transition effect to internal links
    const internalLinks = document.querySelectorAll('a[href^="/"]');
    internalLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            // Skip if modifier keys are pressed or it's a new tab request
            if (e.metaKey || e.ctrlKey || e.shiftKey || e.altKey) {
                return;
            }
            
            // Skip for links that should bypass transition
            if (this.classList.contains('no-transition')) {
                return;
            }
            
            e.preventDefault();
            const targetHref = this.getAttribute('href');
            
            // Start fade out
            pageContent.classList.remove('fade-in');
            
            // Navigate after transition
            setTimeout(() => {
                window.location.href = targetHref;
            }, 300);
        });
    });
}

/**
 * Initialize interactive components
 */
function initComponents() {
    // Highlight active navigation link
    highlightActiveNavLink();
    
    // Initialize any tooltips
    initTooltips();
}

/**
 * Highlight the current page in the navigation
 */
function highlightActiveNavLink() {
    const currentPath = window.location.pathname;
    
    // Select all navigation links
    const navLinks = document.querySelectorAll('header a');
    
    navLinks.forEach(link => {
        const linkPath = link.getAttribute('href');
        
        // Exact match for home page
        if (currentPath === '/' && linkPath === '/') {
            link.classList.add('text-gray-200', 'font-bold');
        }
        // Match for other pages
        else if (currentPath.startsWith(linkPath) && linkPath !== '/') {
            link.classList.add('text-gray-200', 'font-bold');
        }
    });
}

/**
 * Initialize tooltips
 */
function initTooltips() {
    // This is a simple tooltip implementation
    // For a real app, consider using a library like Tippy.js
    const tooltipElements = document.querySelectorAll('[data-tooltip]');
    
    tooltipElements.forEach(element => {
        element.addEventListener('mouseenter', function() {
            const tooltipText = this.getAttribute('data-tooltip');
            
            // Create tooltip element
            const tooltip = document.createElement('div');
            tooltip.className = 'bg-gray-900 text-white px-2 py-1 rounded text-sm absolute z-50 opacity-0 transition-opacity';
            tooltip.textContent = tooltipText;
            tooltip.style.bottom = '100%';
            tooltip.style.left = '50%';
            tooltip.style.transform = 'translateX(-50%)';
            tooltip.style.marginBottom = '5px';
            tooltip.style.whiteSpace = 'nowrap';
            
            // Add tooltip to element
            this.style.position = 'relative';
            this.appendChild(tooltip);
            
            // Show tooltip with small delay
            setTimeout(() => {
                tooltip.style.opacity = '1';
            }, 200);
        });
        
        element.addEventListener('mouseleave', function() {
            const tooltip = this.querySelector('div');
            if (tooltip) {
                tooltip.style.opacity = '0';
                setTimeout(() => {
                    tooltip.remove();
                }, 200);
            }
        });
    });
}
