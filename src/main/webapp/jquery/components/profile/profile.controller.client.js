(function () {
    this.loginUrl = "/jquery/components/login/login.template.client.html";
    this.userService = new UserServiceClient();
    this.id;
    var self = this;
    $(main);

    function main() {
        var urlParams = new URLSearchParams(window.location.search);

        self.id=urlParams.get('uid');

        $('.idGrp').toggle();
        var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
        $('#dateOfBirthFld').datepicker({
            uiLibrary: 'bootstrap4',
            format: 'yyyy-mm-dd',
            maxDate: today
        });
        $('#wbdv-updateProfile').click(updateProfile);
        $('.wbdv-logout').click(logout);
        findAndFillUserDetails();
    }

    function findAndFillUserDetails() {
        userService.findUserById(self.id)
            .then(function (user) {
                if(user.success==false){
                    swal("Error!", user.message, "error").then(function(){
                        window.location.replace(self.loginUrl);
                    });
                }
                $('#userIdFld').val(user.id);
                $('#usernameFld').val(user.username);
                $('#passwordFld').val(user.password);
                $('#firstNameFld').val(user.firstName);
                $('#lastNameFld').val(user.lastName);
                $('#phoneFld').val(user.phone);
                $('#emailFld').val(user.email);
                $('#dateOfBirthFld').val(user.dateOfBirth);
                if (user.role == 'Faculty') {
                    $("#facultyFld").prop("checked", true);
                }
                if (user.role == 'Student') {
                    $("#studentFld").prop("checked", true);
                }
            })
    }

    function updateProfile(event) {
        var userId = $('#userIdFld').val();
        var username = $('#usernameFld').val();
        /*var password = $('#passwordFld').val();*/
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var phone = $('#phoneFld').val();
        var email = $('#emailFld').val();
        var dateOfBirth = $('#dateOfBirthFld').val();
        var roleRadio = $('input[name="roleRadioFld"]');
        var role = roleRadio.filter(':checked').val();

        var user = {
            username: username,
            /*password: password,*/
            firstName: firstName,
            lastName: lastName,
            phone: phone,
            email: email,
            dateOfBirth: dateOfBirth,
            role: role
        };

        userService
            .updateUser(userId, user)
            .then(function () {
                $.notify({
                    message: 'Profile has been Updated'
                }, {
                    type: 'success'
                });
            });
    }

    function logout(event) {
        swal("Logging out!", "You would be logged out", "info").then((value) => {
            window.location.replace(self.loginUrl);
        });

    }

})();