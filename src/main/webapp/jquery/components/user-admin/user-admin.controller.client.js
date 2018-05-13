(function () {
    $(main);

    var userService = new UserServiceClient();

    function main() {
        tbody = $('.wbdv-tbody');
        template = $('.wbdv-template');

        $('.wbdv-create').click(createUserModal);

        findAllUsers();
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
        }).then(function(willDelete) {
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

        var editButton = $(event.currentTarget);
        var userId = editButton
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService.findUserById(userId)
            .then(function(user){
                $('#userIdFld').val(user.id);
                $('#usernameFld').val(user.username);
                $('#passwordFld').val(user.password);
                $('#firstNameFld').val(user.firstName);
                $('#lastNameFld').val(user.lastName);
                $('#createUpdateUserModal').modal('toggle');
            });
    }

    function editUser(event) {
        var userId = $('#userIdFld').val();
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName
        };

        userService
            .updateUser(userId,user)
            .then(function() {
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




})();