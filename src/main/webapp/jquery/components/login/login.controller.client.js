(function () {
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('.wbdv-login').click(login);
    }

    function login() {
        username = $('#usernameFld').val();
        password = $('#passwordFld').val();

        userService.login(username, password)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                    if (myJson.success == false) {
                        swal("Error!", myJson.message, "error");
                    } else {
                        userService.findUserByUsername(username).then(function (user) {
                            window.location.replace("/jquery/components/profile/profile.template.client.html?uid="+user.id);
                        })
                    }
                }
            )
        ;
    }
})();
