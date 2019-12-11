$('#myModal').on('show.bs.modal', function (e) {
    console.log(this);
    let stations = $(e.relatedTarget).data('stations');
    console.log(stations);
    $(this).find('.modal-body input').val(stations);
});

$(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.document.location = $(this).data("href");
    });
});

// $('.example-popover').popover({
//     // trigger: 'focus'
//     trigger: 'hover'
// });

$(function () {
    $('#datetimepicker3').datetimepicker({
        format: 'LT'
    });
});

$(function () {
    $('#datetimepicker4').datetimepicker({
        format: 'L'
    });
});

// $('.popover-dismiss').popover({
//     trigger: 'focus'
// });
