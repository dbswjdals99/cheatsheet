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
        location.href = "/details?idx=" + idx + "&gate=" + gate + "&gateway=" + gateway;
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

    /*------------------
        input::focus
    --------------------*/
    $(".cal-input-div").click(function(){
        $(".cal-input").focus();
    });

    $(".cal-input").on("focusin", function(){
        $(".cal-input-div").css({
            "border":"1px solid green",
            "box-shadow":"0 0 0 2px green",
            "transition": "all 0.3s"
        });
    });
    $(".cal-input").on("focusout", function(){
        $(".cal-input-div").css({
            "box-shadow":"none",
            "border":"1px solid white",
        });
    });

    /*------------------
          쌀먹 계산기 계산
    --------------------*/

    $(document).ready(function () {

        $(".table-clipboard").click(function(){

            const Toast = Swal.mixin({
                toast: true,
                position: 'bottom',
                showConfirmButton: false,
                timer: 2000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            });

            const textArea = document.createElement('textarea');
            textArea.value = $(this).children("input[type=hidden]").val();
            document.body.appendChild(textArea);
            textArea.select();
            textArea.setSelectionRange(0, 99999);
            try {
                document.execCommand('copy');
            } catch (err) {
                console.error('복사 실패', err);
            }
            textArea.setSelectionRange(0, 0);
            document.body.removeChild(textArea);
            Toast.fire({
                icon: 'success',
                title: '복사가 완료되었습니다.'
            })

        });

        $(".cal-button").click(function(){
            $(".cal-button").removeClass("active");
            $("#cal-num").val($(this).val());
            $(this).addClass("active");

            const num = $(this).val();
            const realprice = $("#input").val();

            const resultprice = Mathprice(num, realprice);
            const result2price = resultprice / 1.1;

            $("#a1-gold").text(Math.floor(resultprice)+" G");
            $("#b1-gold").text(Math.floor(result2price)+" G");
            $("#a2-gold").text(Math.floor(realprice-resultprice)+" G");
            $("#b2-gold").text(Math.floor(realprice-result2price)+" G");
            $("#a3-gold").text(Math.floor(resultprice / (num-1))+" G");
            $("#b3-gold").text(Math.floor(result2price / (num-1))+" G");

            $("#cal-clipboard1").val(Math.floor(resultprice));
            $("#cal-clipboard2").val(Math.floor(result2price));

        });

        const input = document.getElementById("input");
        input.addEventListener("input", function(e) {
            const num = $("#cal-num").val();
            const realprice = $(this).val();

            const resultprice = Mathprice(num, realprice);
            const result2price = resultprice / 1.1;

            $("#a1-gold").text(Math.floor(resultprice)+" G");
            $("#b1-gold").text(Math.floor(result2price)+" G");
            $("#a2-gold").text(Math.floor(realprice-resultprice)+" G");
            $("#b2-gold").text(Math.floor(realprice-result2price)+" G");
            $("#a3-gold").text(Math.floor(resultprice / (num-1))+" G");
            $("#b3-gold").text(Math.floor(result2price / (num-1))+" G");

            $("#cal-clipboard1").val(Math.floor(resultprice));
            $("#cal-clipboard2").val(Math.floor(result2price));

        });
    });


    function Mathprice(num, realprice){
        const price = realprice * 0.95;
        const calprice = price / num;
        const resultprice = price - calprice;

        return resultprice;
    }

})(jQuery);