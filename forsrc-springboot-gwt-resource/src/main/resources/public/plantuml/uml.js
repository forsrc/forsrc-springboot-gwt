
(function() {
    var images = document.getElementsByTagName("img");
    var length = images.length;
    var i = 0;
    var uml = "";
    var img = null;
    var url = null;
    for(; i < length; i++) {
        img = images[i];
        uml = img.getAttribute("uml");
        if (uml) {
            url = img.getAttribute("src");
            img.setAttribute("src", url + encodeURI(uml));
        }
    }

})();