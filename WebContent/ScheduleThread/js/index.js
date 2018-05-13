$('a[href*="#"]')
    .not('[href="#"]')
    .not('[href="#0"]')
    .click(function(event) {
        if (
            location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '')
            &&
            location.hostname == this.hostname
        ) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                event.preventDefault();
                $('html, body').animate({
                    scrollTop: target.offset().top
                }, 1000, function() {
                    var $target = $(target);
                    $target.focus();
                    if ($target.is(":focus")) {
                        return false;
                    } else {
                        $target.attr('tabindex','-1');
                        $target.focus();
                    };
                });
            }
        }
    });

$(".quote-id").click(function () {
    var id = $(this).data("quoteId");
    var name = $(this).data("name");

    if($("#textarea").val() != "")
        $("#textarea").val($("#textarea").val() +"\n[quote=\"" + name + "\"]"+ $("#" + id).text() + "[/quote]");
    else
        $("#textarea").val("[quote=\"" + name + "\"]"+ $("#" + id).text() + "[/quote]");

    $('html, body').animate({
        scrollTop: $("#answer").offset().top
    }, 1000);
    $("#textarea").focus();
});

$(".answer").click(function() {
    $('html, body').animate({
        scrollTop: $("#answer").offset().top
    }, 1000);
    $("#textarea").focus();
});
/**
 * © 2017 Sebastian Eggers
 * File: editor.js
 * Date: 04.07.2017
 *
 * All rights reserved.
 */

$("#textarea").focus(function () {
    $("#keyboard").removeClass("hidden-keyboard").addClass("show-keyboard");
    $("#buttons").removeClass("hidden-buttons").addClass("show-buttons");
    $("#textarea").addClass("enchance");
});

var i = 1;
$("#open-keyboard").click(function () {
    if(i%2 !== 0) {
        $("#keys").removeClass("hidden-keys").addClass("show-keys");
        $("#key-icon").removeClass("fa-keyboard-o").addClass("fa-close");
        $("#keyboard").removeClass("show-keyboard").addClass("show-keys");
        $("#textarea").focus();
    }
    else {
        $("#keys").addClass("hidden-keys").removeClass("show-keys");
        $("#key-icon").addClass("fa-keyboard-o").removeClass("fa-close");
        $("#keyboard").addClass("show-keyboard").removeClass("show-keys");
        $("#textarea").focus();
    }
    i++;
});


$(".key-button").click(function () {
    var textarea = $("#textarea").val() + $(this).text();
    $("#textarea").val(textarea).focus();
});

$(document).mouseup(function(e) {
    var $container = $("#editor");
    if (!$container.is(e.target) && $container.has(e.target).length === 0) {
        $("#keyboard").addClass("hidden-keyboard").removeClass("show-keyboard");
        $("#buttons").addClass("hidden-buttons").removeClass("show-buttons");
        $("#textarea").removeClass("enchance");
        $("#keys").addClass("hidden-keys").removeClass("show-keys");
        $("#key-icon").addClass("fa-keyboard-o").removeClass("fa-close");
        i = 1;
    }
});