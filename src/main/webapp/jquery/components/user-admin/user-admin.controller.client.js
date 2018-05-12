(function () {
    $(main);

    var userService = new UserServiceClient();

    function main() {
        tbody = $('.wbdv-tbody');
        template = $('.wbdv-template');

        $('#createUser').click(createUser);

        findAllUsers();
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++){
            var user = users[i];
            var clone = template.clone();

            clone.attr('id',user.id);
            clone.removeClass('wbdv-hidden');
            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            
            tbody.append(clone);
        }
    }

    function createUser(){
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
            .createUser(user)
            .then(findAllUsers);

        $('#createUserModal').modal('toggle');
        $.notify({
            message: 'User has been created'
        },{
            type: 'success'
        });
    }

})();