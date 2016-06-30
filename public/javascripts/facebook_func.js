// set param


function fbUser(accessToken,userID,nameFB,email,expiresIn){
    this.accessToken = accessToken || "";
    this.userID = userID || "";
    this.nameFB = nameFB || "";
    this.email = email || "";
    this.expiresIn = expiresIn || "";
}
var newUser = new fbUser();

var accessToken,userID,expiresIn,email,nameFB;

// Load the SDK asynchronously
(function() {
    var e = document.createElement('script'); e.async = true;
    e.src = document.location.protocol
        + '//connect.facebook.net/en_US/all.js';
    document.getElementById('fb-root').appendChild(e);
}());


/*
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
*/

//========================

function getUserToken(response, info){
    if (response.authResponse) {
        console.log(response.authResponse);
        console.log(info);

        newUser.accessToken = response.authResponse.accessToken;
        newUser.userID = info.id;
        newUser.email = info.email;
        newUser.nameFB = info.name;
        newUser.expiresIn = response.authResponse.expiresIn;
        setFBInfoToPost(response)
    }
}

function setFBInfoToPost(response){
    document.getElementById('access_token').value = newUser.accessToken;
    document.getElementById('userID').value = newUser.userID;
    document.getElementById('expiresIn').value = newUser.expiresIn;
    document.getElementById('email').value = newUser.email;
    document.getElementById('nameFb').value = newUser.nameFB;
    document.getElementById('btnEntry').removeAttribute("disabled");
    document.getElementById('btnEntry').setAttribute("type","submit");
}
// app request

function graphStreamPublish(){
    FB.api('/me/feed', 'post',
        {
            message     : "tutorials",
            link        : 'https://devminigame.mobigame.jp',
            picture     : '',
            name        : 'tutorials',
            description : 'tutorials'
        },
        function(response) {
            if (!response || response.error) {
                console.log(response.error);
                alert('Error occured');
            } else {
                alert('Post ID: ' + response.id);
            }
        });
}
function fqlQuery(){
    FB.api('/me', function(response) {
        //http://developers.facebook.com/docs/reference/fql/user/
        var query =  FB.Data.query('select name, profile_url, sex, pic_small , email from user where uid={0}', response.id);
        query.wait(function(rows) {
            document.getElementById('debug').innerHTML =
                'FQL Information: '+  "<br />" +
                    'Your name: '      +  rows[0].name                                                            + "<br />" +
                    'Your Sex: '       +  (rows[0].sex!= undefined ? rows[0].sex : "")                            + "<br />" +
                    'Your Profile: '   +  "<a href='" + rows[0].profile_url + "'>" + rows[0].profile_url + "</a>" + "<br />" +
                    '<img src="'       +  rows[0].pic_small + '" alt="" />' + "<br />" +
                    'Email '           +  rows[0].email + '' + "<br />" ;
        });
    });
}

function setStatus(){
    status1 = document.getElementById('status').value;
    FB.api('/me/feed', 'post', {message: 'Hello, world!'});
}