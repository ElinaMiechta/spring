$(document).ready(function() {

    $('.color-choose input').on('click', function() {
        var headphonesColor = $(this).attr('data-image');

        $('.active').removeClass('active');
        $('.left-column img[data-image = ' + headphonesColor + ']').addClass('active');
        $(this).addClass('active');
    });

});


$(document).ready(function () {
    // Activate Carousel
    $("#carouselExampleIndicators").carousel("pause");

    // Go to the previous item
    $("#myBtn").click(function () {
        $("#carouselExampleIndicators").carousel("prev");
    });

    // Go to the next item
    $("#myBtn2").click(function () {
        $("#carouselExampleIndicators").carousel("next");
    });

    // Enable Carousel Indicators
    $(".item1").click(function () {
        $("#carouselExampleIndicators").carousel(0);
    });
    $(".item2").click(function () {
        $("#carouselExampleIndicators").carousel(1);
    });
    $(".item3").click(function () {
        $("#carouselExampleIndicators").carousel(2);
    });
    $(".item4").click(function () {
        $("#carouselExampleIndicators").carousel(3);
    });

    // Enable Carousel Controls
    $(".left").click(function () {
        $("#carouselExampleIndicators").carousel("prev");
    });
    $(".right").click(function () {
        $("#carouselExampleIndicators").carousel("next");
    });
});