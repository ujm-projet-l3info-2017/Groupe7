var b_addWp;
var b_addStartWp;
var currentLocation;

    function onMapClick(e)
    {
        var container = L.DomUtil.create('div'),
        startBtn = createButton('Partir d\'ici', container),
   		destBtn = createButton('Arriver ici', container);

        L.DomEvent.on(startBtn, 'click', function() { control.spliceWaypoints(0, 1, e.latlng); myMap.closePopup(); });
        L.DomEvent.on(destBtn, 'click', function() { control.spliceWaypoints(control.getWaypoints().length - 1, 1, e.latlng); myMap.closePopup(); });

        L.popup().setContent(container).setLatLng(e.latlng).openOn(myMap);
    }

    function onLocationFound(e) {
        currentLocation = e.latlng;
        //alert(currentLocation);
    }

    function onLocationError(e)
    {
        //todo : handle error
        alert(e.message);
    }

    function markLocation(latitude,longitude, msg)
    {
        L.marker([latitude, longitude]).addTo(myMap).bindPopup(msg).openPopup();
    }

    function createButton(label, container) {
        var btn = L.DomUtil.create('button', 'btnWp', container);
            btn.setAttribute('type', 'button');
            btn.innerHTML = label;
        return btn;
    }

    function handleError(e) {
            if (e.error.status === -1) {
              document.querySelector('#osrm-error').style.display = 'block';
              L.DomEvent.on(document.querySelector('#osrm-error-close'), 'click', function(e) {
                document.querySelector('#osrm-error').style.display = 'none';
                L.DomEvent.preventDefault(e);
              });
            }
     }

