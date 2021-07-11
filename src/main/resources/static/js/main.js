/**
 *
 */
function userPage(){
    let adminContainer = document.getElementById('adminContainer')
    let userContainer = document.getElementById('userContainer')
    adminContainer.style.display = 'none'
    userContainer.style.display = 'block'
}

function adminPage(){
    let adminContainer = document.getElementById('adminContainer')
    let userContainer = document.getElementById('userContainer')
    adminContainer.style.display = 'block'
    userContainer.style.display = 'none'

}

/*Modal forms open and fill*/
function fillDelModal(id) {
    let href = $(this).attr('href');
    fetch("/findOne/" + id).then(res => res.json()).then(function (user) {
        $.get(href, function () {
            $('.deleteForm #deleteId').val(user.id);
            $('.deleteForm #deleteName').val(user.name);
            $('.deleteForm #deleteSurname').val(user.surname);
            $('.deleteForm #deleteEmail').val(user.email);
            $('.deleteForm #deletePassword').val(user.password);
        })
        $('.deleteForm #deleteModal').modal();
    })
}

function fillEditModal(id) {
    let href = $(this).attr('href');
    fetch("/findOne/" + id).then(res => res.json()).then(function (user) {
        $.get(href, function () {
            $('.editForm #editModalId').val(user.id);
            $('.editForm #editModalName').val(user.name);
            $('.editForm #editModalSurname').val(user.surname);
            $('.editForm #editModalEmail').val(user.email);
            $('.editForm #editModalListOfRoles').val(user.password);
        })
        $('.editForm #editModal').modal();
    })
}

/*refresh table after changing*/
function refreshTable() {
    let table = document.getElementById('tableAll')
    if (table.rows.length > 1) {
        table.deleteRow(1);
    }
    setTimeout(getUsers, 140)
}
/*Кнопка на форме редактирования*/
function edBtn() {
    let id = document.getElementById('editModalId').value;
    console.log(id)
    let firstName = document.getElementById('editModalName').value;
    let lastName = document.getElementById('editModalSurname').value;
    let username = document.getElementById('editModalEmail').value;
    let password = document.getElementById('editModalPassword').value;
    let roles = Array.from(document.getElementById("editModalListOfRoles").selectedOptions).map(r => r.value);
    let user = JSON.stringify({
        id: id,
        name: firstName,
        surname: lastName,
        email: username,
        password: password,
        roles: roles
    })
    console.log(user)
    fetch("/update", {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: user
    }).then(r=>console.log(user))
    $("#editModal .close").click();
    refreshTable();
}
/*Кнопка на форме удаление*/
function delBtn() {
    let uid = document.getElementById('deleteId').value
    fetch("/delete/" + uid, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })
    $("#deleteModal .close").click();
    refreshTable()
}


function getUsers() {
    fetch("/adminAll").then(res => res.json()).then(function (list) {
        let output = '';
        for (let i in list) {
            output += '<tr><td>' + list[i].id
                + '</td><td>' + list[i].name
                + '</td><td>' + list[i].surname
                + '</td><td>' + list[i].email
                + '</td><td>' + list[i].stringRoles
                + '</td><td>' + '<input type="button" onClick="fillEditModal(' + list[i].id + ')" class="btn btn-primary eBtn" value="Edit" id="' + list[i].id + '">'
                + '</td><td>' + '<input type="button" onClick="fillDelModal(' + list[i].id + ')" class="dBtn btn btn-danger" value="Delete" id="' + list[i].id + '">'
                + '</td></tr>';
        }
        $('#tableAll').empty().append(output)
    })
}
getUsers()

function newUserForm(ev){
    ev.preventDefault();
    let firstName = document.getElementById('newUsername').value;
    let lastName = document.getElementById('newUserSurname').value;
    let username = document.getElementById('newUserEmail').value;
    let password = document.getElementById('newUserPassword').value;
    let roles = Array.from(document.getElementById('newUserListOfRoles').selectedOptions).map(r => r.value);
    let user = JSON.stringify({
        name: firstName,
        surname: lastName,
        email: username,
        password: password,
        roles: roles
    })
    fetch("/create", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: user
    })
        .then(() => {
            document.getElementById('home-tab').click();
            document.getElementById('newUserForm').reset();

        })
    refreshTable()
}
$(document).ready(function () {
    $('#myTab a').on('click', function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    fetch("/users").then(res => res.json()).then(function (u) {
        $("#header").text(u.email + " with roles: " + u.stringRoles)
        $("#current-id").text(u.id)
        $("#current-name").text(u.name)
        $("#current-lastname").text(u.surname)
        $("#current-email").text(u.email)
        $("#current-role").text(u.stringRoles)
    })
    // new User
    document.getElementById('newUserForm')
        .addEventListener("submit", newUserForm);
})