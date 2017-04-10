var b_addWp;
var b_addStartWp;

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



