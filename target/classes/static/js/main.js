/**
 * CubeMaster Pro - Modern JavaScript
 * Enhanced interactive features and animations
 */

// Global state management
const CubeMaster = {
    theme: localStorage.getItem('theme') || 'auto',
    animations: true,
    debug: false
};

// Wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    console.log('🎲 CubeMaster Pro - Initializing...');
    
    // Initialize core features
    initThemeSystem();
    initPageTransitions();
    initAnimations();
    initInteractiveComponents();
    initAccessibility();
    
    console.log('✅ CubeMaster Pro - Ready!');
});

/**
 * Enhanced Theme System
 */
function initThemeSystem() {
    const themeToggle = document.getElementById('theme-toggle');
    const themeToggleMobile = document.getElementById('theme-toggle-mobile');
    
    function updateThemeIcons() {
        const isDark = document.documentElement.classList.contains('dark');
        
        // Update desktop icons
        const lightIcon = document.getElementById('light-icon');
        const darkIcon = document.getElementById('dark-icon');
        
        if (lightIcon && darkIcon) {
            lightIcon.classList.toggle('hidden', isDark);
            darkIcon.classList.toggle('hidden', !isDark);
        }
        
        // Update mobile icons
        const lightIconMobile = document.getElementById('light-icon-mobile');
        const darkIconMobile = document.getElementById('dark-icon-mobile');
        
        if (lightIconMobile && darkIconMobile) {
            lightIconMobile.classList.toggle('hidden', isDark);
            darkIconMobile.classList.toggle('hidden', !isDark);
        }
    }
    
    function toggleTheme() {
        document.documentElement.classList.toggle('dark');
        const newTheme = document.documentElement.classList.contains('dark') ? 'dark' : 'light';
        localStorage.setItem('theme', newTheme);
        CubeMaster.theme = newTheme;
        updateThemeIcons();
        
        // Add smooth transition effect
        document.body.style.transition = 'background-color 0.3s ease, color 0.3s ease';
        setTimeout(() => {
            document.body.style.transition = '';
        }, 300);
    }
    
    // Initialize theme
    updateThemeIcons();
    
    // Add event listeners
    if (themeToggle) {
        themeToggle.addEventListener('click', toggleTheme);
    }
    
    if (themeToggleMobile) {
        themeToggleMobile.addEventListener('click', toggleTheme);
    }
}

/**
 * Modern Page Transitions
 */
function initPageTransitions() {
    const pageContent = document.querySelector('main');
    if (!pageContent) return;
    
    // Add initial animation
    pageContent.style.opacity = '0';
    pageContent.style.transform = 'translateY(20px)';
    
    setTimeout(() => {
        pageContent.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
        pageContent.style.opacity = '1';
        pageContent.style.transform = 'translateY(0)';
    }, 100);
    
    // Enhanced link transitions
    const internalLinks = document.querySelectorAll('a[href^="/"], a[href^="' + window.location.origin + '"]');
    
    internalLinks.forEach(link => {
        // Skip if it's an external link or has no-transition class
        if (link.classList.contains('no-transition') || 
            link.getAttribute('target') === '_blank') {
            return;
        }
        
        link.addEventListener('click', function(e) {
            // Skip if modifier keys are pressed
            if (e.metaKey || e.ctrlKey || e.shiftKey || e.altKey) {
                return;
            }
            
            // Skip if same page
            if (this.href === window.location.href) {
                return;
            }
            
            e.preventDefault();
            const targetHref = this.getAttribute('href');
            
            // Add loading state
            document.body.style.cursor = 'wait';
            
            // Fade out with subtle animation
            pageContent.style.opacity = '0.8';
            pageContent.style.transform = 'translateY(-10px)';
            
            setTimeout(() => {
                window.location.href = targetHref;
            }, 200);
        });
    });
}

/**
 * Modern Animations and Interactions
 */
function initAnimations() {
    // Intersection Observer for scroll animations
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };
    
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('animate-fade-in');
                
                // Add staggered animation for children
                const children = entry.target.querySelectorAll('.stagger-child');
                children.forEach((child, index) => {
                    setTimeout(() => {
                        child.classList.add('animate-slide-up');
                    }, index * 100);
                });
            }
        });
    }, observerOptions);
    
    // Observe elements that should animate on scroll
    document.querySelectorAll('.animate-on-scroll').forEach(el => {
        observer.observe(el);
    });
    
    // Add hover effects for cards
    document.querySelectorAll('.hover-lift').forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-8px)';
            this.style.boxShadow = '0 20px 40px rgba(0, 0, 0, 0.15)';
        });
        
        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
            this.style.boxShadow = '';
        });
    });
}

/**
 * Interactive Components
 */
function initInteractiveComponents() {
    // Enhanced button interactions
    document.querySelectorAll('.btn-interactive').forEach(button => {
        button.addEventListener('click', function(e) {
            // Create ripple effect
            const ripple = document.createElement('span');
            const rect = this.getBoundingClientRect();
            const size = Math.max(rect.width, rect.height);
            const x = e.clientX - rect.left - size / 2;
            const y = e.clientY - rect.top - size / 2;
            
            ripple.style.width = ripple.style.height = size + 'px';
            ripple.style.left = x + 'px';
            ripple.style.top = y + 'px';
            ripple.classList.add('ripple');
            
            this.appendChild(ripple);
            
            setTimeout(() => {
                ripple.remove();
            }, 600);
        });
    });
    
    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
    
    // Highlight active navigation link
    highlightActiveNavLink();
}

/**
 * Accessibility Enhancements
 */
function initAccessibility() {
    // Keyboard navigation for custom components
    document.addEventListener('keydown', function(e) {
        // ESC key to close modals/dropdowns
        if (e.key === 'Escape') {
            document.querySelectorAll('.modal-open, .dropdown-open').forEach(el => {
                el.classList.remove('modal-open', 'dropdown-open');
            });
        }
        
        // Enter/Space to activate buttons
        if ((e.key === 'Enter' || e.key === ' ') && e.target.classList.contains('btn-keyboard')) {
            e.preventDefault();
            e.target.click();
        }
    });
    
    // Focus management
    document.addEventListener('focusin', function(e) {
        if (e.target.matches('input, textarea, select, button, a[href]')) {
            e.target.classList.add('keyboard-focused');
        }
    });
    
    document.addEventListener('focusout', function(e) {
        e.target.classList.remove('keyboard-focused');
    });
}

/**
 * Highlight the current page in the navigation
 */
function highlightActiveNavLink() {
    const currentPath = window.location.pathname;
    const navLinks = document.querySelectorAll('header nav a');
    
    navLinks.forEach(link => {
        const linkPath = link.getAttribute('href');
        
        // Remove existing active classes
        link.classList.remove('text-primary-600', 'dark:text-primary-400', 'bg-primary-50', 'dark:bg-primary-900/20');
        
        // Add active state for current page
        if ((currentPath === '/' && linkPath === '/') || 
            (currentPath.startsWith(linkPath) && linkPath !== '/')) {
            link.classList.add('text-primary-600', 'dark:text-primary-400', 'bg-primary-50', 'dark:bg-primary-900/20');
        }
    });
}

/**
 * Utility Functions
 */

// Show notification
function showNotification(message, type = 'info', duration = 3000) {
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.innerHTML = `
        <div class="flex items-center gap-3">
            <svg class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                ${type === 'success' ? '<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />' :
                  type === 'error' ? '<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />' :
                  '<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />'}
            </svg>
            <span>${message}</span>
        </div>
    `;
    
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 16px 20px;
        background: ${type === 'success' ? '#10B981' : type === 'error' ? '#EF4444' : '#3B82F6'};
        color: white;
        border-radius: 12px;
        box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
        z-index: 1000;
        transform: translateX(100%);
        transition: transform 0.3s ease;
        font-weight: 500;
        max-width: 400px;
    `;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.style.transform = 'translateX(0)';
    }, 10);
    
    setTimeout(() => {
        notification.style.transform = 'translateX(100%)';
        setTimeout(() => {
            if (notification.parentNode) {
                notification.parentNode.removeChild(notification);
            }
        }, 300);
    }, duration);
}

// Debounce function for performance
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}
