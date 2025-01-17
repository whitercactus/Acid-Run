const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
canvas.width = canvas.offsetWidth;
canvas.height = canvas.offsetHeight;

ctx.webkitImageSmoothingEnabled = false;
ctx.imageSmoothingEnabled = false;

hexDigits = ["0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"]

//update canvas
function update() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(map.image.file, map.x, map.y, map.image.displayWidth, map.image.displayHeight);

    drawBrushes();
    drawWaypoints();
}


//TODO: remove this
//load map.image.file
map.image.file.src = map.image.src;
map.image.file.onload = function() {
    let imgWidth = map.image.file.naturalWidth;
    let imgHeight = map.image.file.naturalHeight;

    //set base image size and offset
    if(imgHeight <= imgWidth) {
        map.image.baseWidth = map.image.file.naturalHeight * map.scale;
        map.image.baseHeight = map.image.file.naturalWidth * map.scale;

        map.x = (canvas.width - map.image.baseWidth) / 2;
    } else {
        map.image.baseHeight = map.image.file.naturalHeight * map.scale;
        map.image.baseWidth = map.image.file.naturalWidth * map.scale;

        map.y = (canvas.height - map.image.baseHeight) / 2;
    }
    
    //set initial display image size
    map.image.displayWidth = map.image.baseWidth * map.scale;
    map.image.displayHeight = map.image.baseHeight * map.scale;

    //initial image draw
    update();
}

function drawBrushes() {
    for( a in map.toolData.brushes ) { //loop through brushes

        let brush = map.toolData.brushes[a];
        ctx.fillStyle = brush.color;
        ctx.strokeStyle = brush.color;
        ctx.lineCap = 'square';

        for( b in brush.points.x ) { //loop through strokes
            //draw lines
            ctx.lineWidth = brush.thickness * map.scale;
            ctx.beginPath();

            for( c in brush.points.x[b] ) { //loop through points
                x = (brush.points.x[b][c] + 0.5) * (map.scale) + map.x;
                y = (brush.points.y[b][c] + 0.5) * (map.scale) + map.y;

                ctx.moveTo(x, y);

                ctx.lineTo(x, y);
            }
            
            //stroke
            ctx.stroke();
        }
    } 
}

function toHex(value) {
    a = Math.floor(value/16);
    b = Math.floor(value - a*16);

    //a & b are converted to strings here
    a = hexDigits[a];
    b = hexDigits[b];

    return a + b;
}

function drawWaypoints() {
    let x;
    let y;

    // ctx.fillStyle = "black";
    // ctx.font = "30px sans-serif";
    // ctx.textAlign = "center";

    for( i = 0 ; i < map.toolData.waypoints.length ; i++ ) {
        var waypoint = map.toolData.waypoints[i];
        x = waypoint.x * (map.scale) + map.x;
        y = waypoint.y * (map.scale) + map.y;

        // ctx.fillText(waypoint.name, x, y + 3);

        ctx.drawImage(tool.waypoint.icons[waypoint.name], x - 20, y - 20, 40, 40);
    }    
    
}

function scaleCoordinate(coordinate, origin, factor) {
    //scale scale cordinates of point
    xScaled = coordinate + (coordinate - origin) * factor;

    //move point cordinates
    xMoved = (xScaled + origin * factor);

    return xMoved;
}

