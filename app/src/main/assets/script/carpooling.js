    var map;

    function init(webmap)
    {
        map = webmap;
    }

    function onMapClick(e)
    {
        popup
            .setLatLng(e.latlng)
            .setContent("You clicked the map at " + e.latlng.toString())
            .openOn(mymap);
    }

    function onLocationError(e)
    {
        alert(e.message);
    }

    function markLocation(map,lat,long)
    {


    }