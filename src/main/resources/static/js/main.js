/*  ---------------------------------------------------
    Theme Name: Anime
    Description: Anime video tamplate
    Author: Colorib
    Author URI: https://colorib.com/
    Version: 1.0
    Created: Colorib
---------------------------------------------------------  */

'use strict';

(function ($) {

    /*------------------
        Preloader
    --------------------*/
    $(window).on('load', function () {
        $(".loader").fadeOut();
        $("#preloder").delay(200).fadeOut("slow");
        /*------------------
            FIlter
        --------------------*/
        $('.filter__controls li').on('click', function () {
            $('.filter__controls li').removeClass('active');
            $(this).addClass('active');
        });
        if ($('.filter__gallery').length > 0) {
            var containerEl = document.querySelector('.filter__gallery');
            var mixer = mixitup(containerEl);
        }

        $('#buttonsContainer').empty();
        const urlParams = new URL(location.href).searchParams;
        const gate = urlParams.get('gateway');
        var $buttonsContainer = $('#buttonsContainer');

        for (var num = 1; num <= gate; num++) {
            $('<button/>', {
                type: 'button',
                'class': 'btn btn-dark',
                text: num + '번',
                value: num
            }).appendTo($buttonsContainer);
        }


    });

    /*------------------
        Background Set
    --------------------*/

    $(document).ready(function () {
        let video = document.getElementById('videoElement');
        let toggleBtn = document.getElementById('PiP');
        toggleBtn.addEventListener('click', togglePiPMode);
        async function togglePiPMode(event) {
            try {
                if (video !== document.pictureInPictureElement) {
                    await video.requestPictureInPicture();
                    toggleBtn.textContent = "PIP 모드 종료";
                }
                else {
                    await document.exitPictureInPicture();
                    toggleBtn.textContent = "PIP 모드 시작";
                }
            } catch (error) {
                console.log(error);
            }
        }
    });

    $(document).ready(function(){
        $('.details-popup').on('click', function(){
            var src = $(this).attr('src');

            var nWidth = "1920";

            var nHeight = "1080";

            var xPos = (document.body.clientWidth / 2) - (nWidth / 2);

            xPos += window.screenLeft;  //듀얼 모니터

            var yPos = (screen.availHeight / 2) - (nHeight / 2);

            window.open(
                'imgView?src=' + encodeURIComponent(src),
                'Image View',
                'noopener, resizable=yes, width='+nWidth+',height='+nHeight+', left='+xPos+', top='+yPos+''
            );
        });
    });

    $('.set-bg').each(function () {
        var bg = $(this).data('setbg');
        $(this).css('background-image', 'url(' + bg + ')');
    });

    $(".header__menu ul li").on("click",function(e){
        var el = $(e.target).closest('li');
        // 다른 active되어있는 li의 class를 제거
        el.siblings('li').removeClass("active");
        el.addClass("active");
    });

    $('.btn-gate').on('click', '.btn-dark', function () {
        const urlParams = new URL(location.href).searchParams;
        const idx = urlParams.get('idx');
        const gateway = urlParams.get('gateway');
        const gate = $(this).val();
        const gubun = urlParams.get('gubun');
        location.href = "/LostArk/details?idx=" + idx + "&gate=" + gate + "&gateway=" + gateway + "&gubun=" + gubun;
    });

    // Search model
    $('.search-switch').on('click', function () {
        $('#buttonsContainer').empty();
        document.body.style.overflow = "hidden";
        var gate = $(this).attr('value');
        var $buttonsContainer = $('#buttonsContainer');

        for (var num = 1; num <= gate; num++) {
            $('<button/>', {
                type: 'button',
                'class': 'btn btn-dark',
                text: num + '번',
        }).appendTo($buttonsContainer);
        }

        $('.search-model').fadeIn(400);
    });

    $('.search-close-switch').on('click', function () {
        $('.search-model').fadeOut(400, function () {
            $('#search-input').val('');
        });
    });

    /*------------------
		Navigation
	--------------------*/
    $(".mobile-menu").slicknav({
        prependTo: '#mobile-menu-wrap',
        allowParentLinks: true
    });

    /*------------------
		Hero Slider
	--------------------*/
    var hero_s = $(".hero__slider");
    hero_s.owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        dots: true,
        nav: true,
        navText: ["<span class='arrow_carrot-left'></span>", "<span class='arrow_carrot-right'></span>"],
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
        mouseDrag: false
    });

    /*------------------
        Video Player
    --------------------*/
    const player = new Plyr('#player', {
        controls: ['play-large', 'play', 'progress', 'current-time', 'mute', 'captions', 'settings', 'fullscreen'],
        seekTime: 25
    });

    /*------------------
        Niceselect
    --------------------*/
    $('select').niceSelect();

    /*------------------
        Scroll To Top
    --------------------*/
    $("#scrollToTopButton").click(function() {
        $("html, body").animate({ scrollTop: 0 }, "slow");
        return false;
     });

})(jQuery);