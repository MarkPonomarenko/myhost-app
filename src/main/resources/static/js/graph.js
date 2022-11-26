// // For drawing the lines
var months = ["Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"];

var money = [document.getElementById("money1").getAttribute("value"), document.getElementById("money2").getAttribute("value"),
    document.getElementById("money3").getAttribute("value"), document.getElementById("money4").getAttribute("value"),
    document.getElementById("money5").getAttribute("value"), document.getElementById("money6").getAttribute("value"),
    document.getElementById("money7").getAttribute("value"), document.getElementById("money8").getAttribute("value"),
    document.getElementById("money9").getAttribute("value"), document.getElementById("money10").getAttribute("value"),
    document.getElementById("money11").getAttribute("value"), document.getElementById("money12").getAttribute("value")]
var ctx = document.getElementById("myChart");
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: months,
        datasets: [{
            data: money,
            label: "Donates",
            borderColor: "#3e95cd",
            fill: false
        }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Year Incomes'
        }
    }
});

var counter = document.getElementById("daysCount").getAttribute("value");
var day;
var days;
var dmoney;
if (counter === "28") {
    console.log(counter);
    day = [document.getElementById("daymoney1").getAttribute("value"), document.getElementById("daymoney2").getAttribute("value"), document.getElementById("daymoney3").getAttribute("value"), document.getElementById("daymoney4").getAttribute("value"), document.getElementById("daymoney5").getAttribute("value"), document.getElementById("daymoney6").getAttribute("value"), document.getElementById("daymoney7").getAttribute("value"), document.getElementById("daymoney8").getAttribute("value"), document.getElementById("daymoney9").getAttribute("value"), document.getElementById("daymoney10").getAttribute("value"), document.getElementById("daymoney11").getAttribute("value"), document.getElementById("daymoney12").getAttribute("value"), document.getElementById("daymoney13").getAttribute("value"), document.getElementById("daymoney14").getAttribute("value"), document.getElementById("daymoney15").getAttribute("value"), document.getElementById("daymoney16").getAttribute("value"), document.getElementById("daymoney17").getAttribute("value"), document.getElementById("daymoney18").getAttribute("value"), document.getElementById("daymoney19").getAttribute("value"), document.getElementById("daymoney20").getAttribute("value"), document.getElementById("daymoney21").getAttribute("value"), document.getElementById("daymoney22").getAttribute("value"), document.getElementById("daymoney23").getAttribute("value"), document.getElementById("daymoney24").getAttribute("value"), document.getElementById("daymoney25").getAttribute("value"), document.getElementById("daymoney26").getAttribute("value"), document.getElementById("daymoney27").getAttribute("value"), document.getElementById("daymoney28").getAttribute("value")]
    days = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28];
    dmoney = [day[0], day[1], day[2], day[3], day[4], day[5], day[6], day[7], day[8], day[9], day[10], day[11],
        day[12], day[13], day[14], day[15], day[16], day[17], day[18], day[19], day[20], day[21], day[22], day[23],
        day[24], day[25], day[26], day[27]]
} else if (counter === "29") {
    console.log(counter);
    day = [document.getElementById("daymoney1").getAttribute("value"), document.getElementById("daymoney2").getAttribute("value"), document.getElementById("daymoney3").getAttribute("value"), document.getElementById("daymoney4").getAttribute("value"), document.getElementById("daymoney5").getAttribute("value"), document.getElementById("daymoney6").getAttribute("value"), document.getElementById("daymoney7").getAttribute("value"), document.getElementById("daymoney8").getAttribute("value"), document.getElementById("daymoney9").getAttribute("value"), document.getElementById("daymoney10").getAttribute("value"), document.getElementById("daymoney11").getAttribute("value"), document.getElementById("daymoney12").getAttribute("value"), document.getElementById("daymoney13").getAttribute("value"), document.getElementById("daymoney14").getAttribute("value"), document.getElementById("daymoney15").getAttribute("value"), document.getElementById("daymoney16").getAttribute("value"), document.getElementById("daymoney17").getAttribute("value"), document.getElementById("daymoney18").getAttribute("value"), document.getElementById("daymoney19").getAttribute("value"), document.getElementById("daymoney20").getAttribute("value"), document.getElementById("daymoney21").getAttribute("value"), document.getElementById("daymoney22").getAttribute("value"), document.getElementById("daymoney23").getAttribute("value"), document.getElementById("daymoney24").getAttribute("value"), document.getElementById("daymoney25").getAttribute("value"), document.getElementById("daymoney26").getAttribute("value"), document.getElementById("daymoney27").getAttribute("value"), document.getElementById("daymoney28").getAttribute("value"), document.getElementById("daymoney29").getAttribute("value")]
    days = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29];
    dmoney = [day[0], day[1], day[2], day[3], day[4], day[5], day[6], day[7], day[8], day[9], day[10], day[11],
        day[12], day[13], day[14], day[15], day[16], day[17], day[18], day[19], day[20], day[21], day[22], day[23],
        day[24], day[25], day[26], day[27], day[28]]
} else if (counter === "30") {
    console.log(counter);
    day = [document.getElementById("daymoney1").getAttribute("value"), document.getElementById("daymoney2").getAttribute("value"), document.getElementById("daymoney3").getAttribute("value"), document.getElementById("daymoney4").getAttribute("value"), document.getElementById("daymoney5").getAttribute("value"), document.getElementById("daymoney6").getAttribute("value"), document.getElementById("daymoney7").getAttribute("value"), document.getElementById("daymoney8").getAttribute("value"), document.getElementById("daymoney9").getAttribute("value"), document.getElementById("daymoney10").getAttribute("value"), document.getElementById("daymoney11").getAttribute("value"), document.getElementById("daymoney12").getAttribute("value"), document.getElementById("daymoney13").getAttribute("value"), document.getElementById("daymoney14").getAttribute("value"), document.getElementById("daymoney15").getAttribute("value"), document.getElementById("daymoney16").getAttribute("value"), document.getElementById("daymoney17").getAttribute("value"), document.getElementById("daymoney18").getAttribute("value"), document.getElementById("daymoney19").getAttribute("value"), document.getElementById("daymoney20").getAttribute("value"), document.getElementById("daymoney21").getAttribute("value"), document.getElementById("daymoney22").getAttribute("value"), document.getElementById("daymoney23").getAttribute("value"), document.getElementById("daymoney24").getAttribute("value"), document.getElementById("daymoney25").getAttribute("value"), document.getElementById("daymoney26").getAttribute("value"), document.getElementById("daymoney27").getAttribute("value"), document.getElementById("daymoney28").getAttribute("value"), document.getElementById("daymoney29").getAttribute("value"), document.getElementById("daymoney30").getAttribute("value")]
    days = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30];
    dmoney = [day[0], day[1], day[2], day[3], day[4], day[5], day[6], day[7], day[8], day[9], day[10], day[11],
        day[12], day[13], day[14], day[15], day[16], day[17], day[18], day[19], day[20], day[21], day[22], day[23],
        day[24], day[25], day[26], day[27], day[28], day[29]]
} else {
    console.log(counter);
    day = [document.getElementById("daymoney1").getAttribute("value"), document.getElementById("daymoney2").getAttribute("value"), document.getElementById("daymoney3").getAttribute("value"), document.getElementById("daymoney4").getAttribute("value"), document.getElementById("daymoney5").getAttribute("value"), document.getElementById("daymoney6").getAttribute("value"), document.getElementById("daymoney7").getAttribute("value"), document.getElementById("daymoney8").getAttribute("value"), document.getElementById("daymoney9").getAttribute("value"), document.getElementById("daymoney10").getAttribute("value"), document.getElementById("daymoney11").getAttribute("value"), document.getElementById("daymoney12").getAttribute("value"), document.getElementById("daymoney13").getAttribute("value"), document.getElementById("daymoney14").getAttribute("value"), document.getElementById("daymoney15").getAttribute("value"), document.getElementById("daymoney16").getAttribute("value"), document.getElementById("daymoney17").getAttribute("value"), document.getElementById("daymoney18").getAttribute("value"), document.getElementById("daymoney19").getAttribute("value"), document.getElementById("daymoney20").getAttribute("value"), document.getElementById("daymoney21").getAttribute("value"), document.getElementById("daymoney22").getAttribute("value"), document.getElementById("daymoney23").getAttribute("value"), document.getElementById("daymoney24").getAttribute("value"), document.getElementById("daymoney25").getAttribute("value"), document.getElementById("daymoney26").getAttribute("value"), document.getElementById("daymoney27").getAttribute("value"), document.getElementById("daymoney28").getAttribute("value"), document.getElementById("daymoney29").getAttribute("value"), document.getElementById("daymoney30").getAttribute("value"), document.getElementById("daymoney31").getAttribute("value")];
    days = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];
    dmoney = [day[0], day[1], day[2], day[3], day[4], day[5], day[6], day[7], day[8], day[9], day[10], day[11],
        day[12], day[13], day[14], day[15], day[16], day[17], day[18], day[19], day[20], day[21], day[22], day[23],
        day[24], day[25], day[26], day[27], day[28], day[29], day[30]]
}

var crx = document.getElementById("myMont");
var myMont = new Chart(crx, {
    type: 'line',
    data: {
        labels: days,
        datasets: [{
            data: dmoney,
            label: "Donates",
            borderColor: "green",
            fill: false
        }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Month Incomes'
        }
    }
});