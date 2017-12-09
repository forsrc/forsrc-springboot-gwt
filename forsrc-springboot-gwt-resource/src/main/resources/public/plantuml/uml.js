
(function() {
    var url = "/plantuml/svg?uml="
    var images = document.getElementsByTagName("img");
    var length = images.length;
    var i = 0;
    var uml = "";
    var img = null;
    for(; i < length; i++) {
        img = images[i];
        uml = img.getAttribute("uml");
        img.setAttribute("src", url + encodeURI(uml));
    }

})();