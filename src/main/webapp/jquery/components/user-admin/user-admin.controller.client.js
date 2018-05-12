(function () {
    $(main);

    var userService = new UserServiceClient();

    function main() {
        tbody = $('.wbdv-tbody');
        template = $('.wbdv-template');

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

})();