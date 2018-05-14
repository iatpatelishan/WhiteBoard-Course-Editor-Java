(function () {
    var userService = new UserServiceClient();
    $(main);

    $(document).ready(function () {
        var urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('reset')) {
            var id = urlParams.get('reset');
            $('#resetIdFld').val(id);
            $('#resetFinalModal').modal('show');
        }
    });

    function main() {
        $('.wbdv-login').click(login);
        $('.wbdv-reset').click(reset);
        $('.wbdv-reset-final').click(resetFinal);
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
                            window.location.replace("/jquery/components/profile/profile.template.client.html?uid=" + user.id);
                        })
                    }
                }
            )
        ;
    }

    function reset(event) {
        username = $('#resetUsernameFld').val();
        userService.forgotPassword(username)
            .then(function () {
                swal("Success!", "If Username exist and has email associated, then reset link sent to email.", "success");
            });
    }

    function resetFinal(event) {
        var id = $('#resetIdFld').val();
        var password = $('#resetPasswordFld').val();
        userService.findUserById(id)
            .then(function (user) {
                user.password=password;
                return userService.updateUser(id,user);
            })
            .then(function(user) {
                $('#resetFinalModal').modal('hide');
                swal("Success!", "Password Reset Successfull! You can now login!.", "success");
            });

    }

})();
