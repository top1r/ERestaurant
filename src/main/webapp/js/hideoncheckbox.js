$(function () {
    $('.addToCartButton').hide();

    //show it when the checkbox is clicked
    $('input[type="radio"]').on('click', function () {
        if ($(this).prop('checked')) {
            $('.addToCartButton').fadeIn();
        } else {
            $('.addToCartButton').hide();
        }
    });
});