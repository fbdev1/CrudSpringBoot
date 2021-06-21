/**
 *
 */

$(document).ready(function () {

    $('#myTab a').on('click', function (e) {
        e.preventDefault()
        $(this).tab('show')
    })

    $('.dBtn').on('click', function (ev) {
        ev.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user) {
            $('.deleteForm #deleteId').val(user.id);
            $('.deleteForm #deleteName').val(user.name);
            $('.deleteForm #deleteSurname').val(user.surname);
            $('.deleteForm #deleteEmail').val(user.email);
            $('.deleteForm #deletePassword').val(user.password);
        })
        $('.deleteForm #deleteModal').modal();
    })


    $('.eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        // var text = $(this).text();
        // var e = document.getElementById('id-field'); //скрывать поле id
        // if (text === 'Edit') {
        $.get(href, function (user) {
            // e.style.display = 'block';
            $('.editForm #id').val(user.id);
            $('.editForm #name').val(user.name);
            $('.editForm #surname').val(user.surname);
            $('.editForm #email').val(user.email);
            $('.editForm #password').val(user.password);

            // if (user.roles.length > 1) {
            //     for (let i = 0; i < user.roles.length; i++) {
            //         listOfRoles.options[i].selected = true;
            //     }
            // } else {
            //     listOfRoles.value = user.roles[0].role;
            // }

        })
        $('.editForm #editModal').modal();
    })
})