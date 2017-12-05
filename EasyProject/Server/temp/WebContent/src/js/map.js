
var mysql = require('mysql');
var connection = mysql.createConnection({
	host:'192.168.0.156',
	user:'Kang',
	password:'1234'
})

connection.query('use easycafe');


function initMap() {
    var sinseol = 	{lat: 37.575097, lng: 127.024776};
    var jegi = 		{lat: 37.5781662, lng: 127.0327063};
    var anyang = 	{lat: 37.4018448, lng: 126.9199753};
    
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 17,
      center: sinseol
    });
    var marker = new google.maps.Marker({
      position: sinseol,
      map: map
    });
    
    var marker = new google.maps.Marker({
        position: jegi,
        map: map
      });
    
    var marker = new google.maps.Marker({
        position: anyang,
        map: map
      });
  };