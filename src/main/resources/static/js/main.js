/**
 *
 */
$(document).ready(function () {
    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit')
        {
            $.get(href, function (user) {
                $('.myForm #id').val(user.id);
                $('.myForm #name').val(user.name);
                $('.myForm #surname').val(user.surname);
                $('.myForm #email').val(user.email);
                // $('.myForm #role').val(user.role);
            });
            $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #surname').val('');
            $('.myForm #email').val('');
            // $('.myForm #role').val(user.role);
            $('.myForm #exampleModal').modal();
        }


    });
});