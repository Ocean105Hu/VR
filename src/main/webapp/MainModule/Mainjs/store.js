$(document).ready(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const username = urlParams.get('username');
    if (username != null) {
        $('.nav_login a').text(username);
    }
    $('.lattice').click(function () {
        var index = $(this).attr('data-index');
        sessionStorage.setItem('index', index);
        window.open('vr.html');
    });
    $(document).ready(function () {
        $('.bar_content').click(function () {
            console.log($(this).index());
            if ($(this).index() === 0) {
                window.location.href = `Main.html?username=${encodeURIComponent(username)}`;
            } else if ($(this).index() === 1) {
                window.location.href = 'storehouse.html';
            } else if ($(this).index() === 4) {
                window.location.href = 'message.html';
            }
        });
    });

})