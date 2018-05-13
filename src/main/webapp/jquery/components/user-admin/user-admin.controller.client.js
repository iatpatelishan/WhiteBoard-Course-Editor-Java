(function () {
    $(main);

    var userService = new UserServiceClient();

    function main() {
        tbody = $('.wbdv-tbody');
        template = $('.wbdv-template');

        $('.wbdv-create').click(createUserModal);
        $('.wbdv-retrieve').click(retrieveUserModal);

        $("#wbdv-mySearchInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("table tbody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });

        findAllUsers();
        $('.retrieveGrp').toggle();
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);
            clone.removeClass('wbdv-hidden');

            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(editUserModal);

            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);

            tbody.append(clone);
        }
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this user!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then(function (willDelete) {
            if (willDelete) {
                userService
                    .deleteUser(userId)
                    .then(findAllUsers);
                swal("Poof! User has been deleted!", {
                    icon: "success",
                });
            } else {
                swal("User is safe!");
            }
        });
    }


    function editUserModal(event) {
        $('#modalTitle').html('Edit User');
        $('#modalSubmit').html('Update');
        $('#modalSubmit').unbind("click").click(editUser);
        $('.retrieveGrp').hide();
        $('.createGrp').show();

        var editButton = $(event.currentTarget);
        var userId = editButton
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService.findUserById(userId)
            .then(function (user) {
                $('#userIdFld').val(user.id);
                $('#usernameFld').val(user.username);
                $('#passwordFld').val(user.password);
                $('#firstNameFld').val(user.firstName);
                $('#lastNameFld').val(user.lastName);
                if (user.role == 'Faculty') {
                    $("#facultyFld").prop("checked", true);
                }
                if (user.role == 'Student') {
                    $("#studentFld").prop("checked", true);
                }
                $('#createUpdateUserModal').modal('toggle');
            });
    }

    function editUser(event) {
        var userId = $('#userIdFld').val();
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var roleRadio = $('input[name="roleRadioFld"]');
        var role = roleRadio.filter(':checked').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService
            .updateUser(userId, user)
            .then(function () {
                $.notify({
                    message: 'User has been Updated'
                }, {
                    type: 'success'
                });
            })
            .then(findAllUsers);

        $('#createUpdateUserModal').modal('toggle');
    }

    function createUserModal() {
        $('#modalTitle').html('Create User');
        $('#modalSubmit').html('Create');
        $('#modalSubmit').unbind("click").click(createUser);
        $('#createUpdateUserModal').modal('toggle');
        $('.retrieveGrp').hide();
        $('.createGrp').show();
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var roleRadio = $('input[name="roleRadioFld"]');
        var role = roleRadio.filter(':checked').val();


        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService
            .createUser(user)
            .then(findAllUsers);

        $('#createUpdateUserModal').modal('toggle');
        $.notify({
            message: 'User has been created'
        }, {
            type: 'success'
        });
    }

    function retrieveUserModal() {
        $('#modalTitle').html('Find User');
        $('#modalSubmit').html('Retrieve');
        $('#modalSubmit').unbind("click").click(retrieveUser);
        $('#createUpdateUserModal').modal('toggle');
        $('.retrieveGrp').show();
        $('.createGrp').hide();
    }

    function retrieveUser() {
        $('#userIdFld').removeClass('is-invalid');
        var userId = $('#userIdFld').val();
        userService.findUserById(userId)
            .then(function (user) {
                fillFormWithUser(user);
                $('#modalTitle').html('Edit User');
                $('#modalSubmit').html('Update');
                $('#modalSubmit').unbind("click").click(editUser);
                $('.retrieveGrp').hide();
                $('.createGrp').show();
                return;
            })
            .catch(function (error) {
                $('#userIdFld').addClass('is-invalid');
            });
    }

    function fillFormWithUser(user) {
        $('#userIdFld').val(user.id);
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        if (user.role == 'Faculty') {
            $("#facultyFld").prop("checked", true);
        }
        if (user.role == 'Student') {
            $("#studentFld").prop("checked", true);
        }
    }


})();