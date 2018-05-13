(function () {
    var userService = new UserServiceClient();
    $(main);


    function main() {
        $('.wbdv-register').click(register);
    }

    function register(event) {
        username = $('#usernameFld').val();
        password = $('#passwordFld').val();
        verifyPasswordFld = $('#verifyPasswordFld').val();

        if(password!=verifyPasswordFld) {
            swal("Error!", "Password Doesnt Match", "error");
            return;
        }

        var user = {
            username : username,
            password : password,
            role : 'Student'
        }
        userService.register(user)
            .then(function (response) {
                    if(response.status == 409) {
                        swal("Error!", "User exists!", "error");

                    } else {
                        swal("Success!", "User has been created!", "success");
                        $('.wbdv-form').find("input[type=text], textarea, input[type=password]").val("");
                    }
                }
            );
    }
})();
