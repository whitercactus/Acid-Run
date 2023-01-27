let tool = {
    selTool: poly,
    toolActive: false,

    brush: {
        selBrush: 0, //index of the selected brush inside brushTool.brushes

        //methods
        addBrush: function (color, thickness) {
            let brush = new Brush(color, thickness);
        
            map.toolData.brushes.push(brush);
            //brushTool.selBrush = brushTool.brushes.length - 1;
            ui.addBrushBtn(color);
        },
    },
    waypoint: {
        selWaypoint: -1,
        icons: [

        ],

        addIcon: (src) => {
            var icon = new Image();
            icon.src = src;
            icon.onload = function() {
                console.log("hello");
            }

            tool.waypoint.icons.push(icon);
        }
    }
}

for(i = 0; i <= 6; i++) {
    tool.waypoint.addIcon(`assets/waypoints/${i}.svg`);
}