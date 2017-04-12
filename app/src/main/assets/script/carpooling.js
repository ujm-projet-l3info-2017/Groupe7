var b_addWp;
var b_addStartWp;
var currentLocation;

    function onMapClick(e)
    {
        var container = L.DomUtil.create('div');
        b_addStartWp = createButton("Départ", container);
        b_addDestinationWp = createButton("Arrivée", container);


        L.popup().setContent(container).setLatLng(e.latlng).openOn(mymap);

         L.DomEvent.on(b_addStartWp, 'click', function() {
                    control.spliceWaypoints(0, 1, e.latlng);
                    mymap.closePopup();
         });

         L.DomEvent.on(b_addDestinationWp, 'click', function() {
                     control.spliceWaypoints(control.getWaypoints().length - 1, 1, e.latlng);
                     mymap.closePopup();
         });
    }

    function onLocationFound(e) {
        currentLocation = e.latlng;
        alert(currentLocation);
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

