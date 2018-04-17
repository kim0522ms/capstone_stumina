var input = document.querySelector('.search__input');
var inputWidth = input.offsetWidth;
var suggestion = document.querySelector('.suggestion');
var mobileSearch = document.querySelector('.search__icon');
var menu = document.querySelector('.menu');
var logo = document.querySelector('.logo');
var logoIcon = logo.querySelector('.logo__icon');

   
function equalWidth() {
  var input = document.querySelector('.search__input');
  var inputWidth = input.offsetWidth;
  suggestion.style.width = inputWidth + "px" ;
}

function showSuggestion() {
  suggestion.classList.add('active');
}

function hideSuggestion() {
  suggestion.classList.remove('active');
}

document.addEventListener('DOMContentLoaded', function() {
  equalWidth();
  input.addEventListener('focus', showSuggestion);
})

document.addEventListener('click', function(e) {
  var target = e.target.className;
  e.stopPropagation();
  if (target != "search__input" && target != "suggestion__content-left-side" && target != "suggestion__content-right-side" && target != "suggestion__content" ) {
    hideSuggestion();
    console.log(target);
  }
});

var addEvent = function(object, type, callback) {
    if (object == null || typeof(object) == 'undefined') return;
    if (object.addEventListener) {
        object.addEventListener(type, callback, false);
    } else if (object.attachEvent) {
        object.attachEvent("on" + type, callback);
    } else {
        object["on"+type] = callback;
    }
};

addEvent(window, 'resize', function(event) {
  suggestion.style.width = '';
  equalWidth();
});

function openMobileSearch() {
  logo.classList.toggle('hide-element');
  menu.classList.toggle('hide-element');
  input.classList.toggle('mobile-search');
  mobileSearch.classList.toggle('ion-ios-search');
  mobileSearch.classList.toggle('ion-close-round');
  mobileSearch.classList.toggle('close-icon');
}

mobileSearch.addEventListener('click', function() {
  openMobileSearch();
})