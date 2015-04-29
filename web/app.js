$('#add-row').on('click', function(e){
    e.preventDefault();
    var tableBody = $('.hospital > tbody'), 
      lastRowClone = $('tr:last-child', tableBody).clone();
    $('input[type=text]', lastRowClone).val('');  
    tableBody.append(lastRowClone);
});
$('.hospital').on('click', '.remove-row', function(e){
  e.preventDefault();
  var row = $(this).parent().parent();
  row.remove();
})

$('#add-row-deliveryNormal').on('click', function(e){
    e.preventDefault();
    var tableBody = $('.deliveryNormal > tbody'),
        lastRowClone = $('tr:last-child', tableBody).clone();
    $('input[type=text]', lastRowClone).val('');
    tableBody.append(lastRowClone);
});
$('.deliveryNormal').on('click', '.remove-row', function(e){
    e.preventDefault();
    var row = $(this).parent().parent();
    row.remove();
})

$('#add-row-deliveryCesarean').on('click', function(e){
    e.preventDefault();
    var tableBody = $('.deliveryCesarean > tbody'),
      lastRowClone = $('tr:last-child', tableBody).clone();
    $('input[type=text]', lastRowClone).val('');  
    tableBody.append(lastRowClone);
});
$('.deliveryCesarean').on('click', '.remove-row', function(e){
  e.preventDefault();
  var row = $(this).parent().parent();
  row.remove();
})

$('#add-row-deliveryPremature').on('click', function(e){
    e.preventDefault();
    var tableBody = $('.deliveryPremature > tbody'),
      lastRowClone = $('tr:last-child', tableBody).clone();
    $('input[type=text]', lastRowClone).val('');  
    tableBody.append(lastRowClone);
});
$('.deliveryPremature').on('click', '.remove-row', function(e){
  e.preventDefault();
  var row = $(this).parent().parent();
  row.remove();
})

$('#add-row-deliveryEctopic').on('click', function(e){
e.preventDefault();
var tableBody = $('.deliveryEctopic > tbody'),
    lastRowClone = $('tr:last-child', tableBody).clone();
$('input[type=text]', lastRowClone).val('');
tableBody.append(lastRowClone);
});
$('.deliveryEctopic').on('click', '.remove-row', function(e){
    e.preventDefault();
    var row = $(this).parent().parent();
    row.remove();
})