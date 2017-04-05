var startBtn = createButton("Position de départ");
var destBtn = createButton('Position d\'arrivée');

    function onMapClick(e)
    {
         var container = L.DomUtil.create('div'),
                    startBtn = createButton('Position de départ', container),
                    destBtn = createButton('Position d\'arrivée', container);
         L.popup().setContent(container).setLatLng(e.latlng).openOn(mymap);
    }

    function onLocationError(e)
    {
        alert(e.message);
    }

    function markLocation(latitude,longitude, msg)
    {
        L.marker([latitude, longitude]).addTo(mymap).bindPopup(msg).openPopup();
    }

    function createButton(label, container) {
        var btn = L.DomUtil.create('button', '', container);

        btn.setAttribute('type', 'button');
        btn.innerHTML = label;

        return btn;
    }

     L.DomEvent.on(startBtn, 'click', function() {
            control.spliceWaypoints(0, 1, e.latlng);
            map1.closePopup();
        });

     L.DomEvent.on(destBtn, 'click', function() {
             control.spliceWaypoints(control.getWaypoints().length - 1, 1, e.latlng);
             map1.closePopup();
         });
