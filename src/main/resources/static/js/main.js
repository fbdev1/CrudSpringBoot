/**
 *
 */
$(document).ready(function () {
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (user){
            $('.myForm #id').val(user.id);
            $('.myForm #name').val(user.name);
            $('.myForm #surname').val(user.surname);
            $('.myForm #email').val(user.email);
            // $('.myForm #role').val(user.role);
        })
        $('.myForm #exampleModal').modal();



    });
});