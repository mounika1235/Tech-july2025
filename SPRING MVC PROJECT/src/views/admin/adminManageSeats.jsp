<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${pageTitle}</title>
    <style>
        .seat-grid { display: grid; grid-template-columns: repeat(${template.cols}, 40px); gap: 6px; justify-content: center; }
        .seat { width: 40px; height: 40px; border-radius: 4px; text-align: center; line-height: 40px; cursor: pointer; }
        .seat.regular { background-color: #6c757d; color: white; }
        .seat.vip { background-color: #ffc107; color: black; }
        .seat.empty { background-color: #e9ecef; color: #adb5bd; }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2>${pageTitle}</h2>

    <div class="mb-3 text-center">
        <button class="btn btn-secondary" onclick="setType('regular')">Regular</button>
        <button class="btn btn-warning" onclick="setType('vip')">VIP</button>
        <button class="btn btn-light" onclick="setType('empty')">Empty</button>
    </div>

    <div id="seatGrid" class="seat-grid"></div>

    <div class="mt-3 text-center">
        <button class="btn btn-primary" onclick="saveLayout()"> Save Layout</button>
        <a href="${pageContext.request.contextPath}/admin/templates/screen/${template.screen.id}" class="btn btn-secondary">Cancel</a>
    </div>
</div>

<script>
	const rows = ${template.rows_no};
	const cols = ${template.cols};
    let currentType = 'regular';
    const grid = document.getElementById('seatGrid');
    const seats = [];

    // Load existing seat data from server - FIXED: Remove seatsJson reference
    const existingSeats = [];

    // Render grid
    for (let r = 1; r <= rows_no; r++) {
        for (let c = 1; c <= cols; c++) {
            const seatDiv = document.createElement('div');
            seatDiv.className = 'seat regular';
            seatDiv.dataset.row = r;
            seatDiv.dataset.col = c;
            seatDiv.dataset.type = 'regular';
            seatDiv.textContent = String.fromCharCode(64 + r) + c;

            seatDiv.onclick = function () {
                if (seatDiv.dataset.type === currentType) return;
                seatDiv.dataset.type = currentType;
                seatDiv.className = 'seat ' + currentType;
            };
            grid.appendChild(seatDiv);
        }
    }

    function setType(type) { currentType = type; }

    function saveLayout() {
        const layout = [];
        document.querySelectorAll('.seat').forEach(seat => {
            layout.push({
                rowLabel: String.fromCharCode(64 + parseInt(seat.dataset.row)),
                col: parseInt(seat.dataset.col),
                seatCode: String.fromCharCode(64 + parseInt(seat.dataset.row)) + seat.dataset.col,
                seatType: seat.dataset.type.toUpperCase(),
                price: seat.dataset.type === 'vip' ? 300 : 200
            });
        });

        fetch('${pageContext.request.contextPath}/admin/seats/saveLayout/${template.id}', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(layout)
        }).then(res => res.text()).then(resp => {
            if (resp === 'SUCCESS') {
                alert('Layout saved successfully!');
                window.location.href = '${pageContext.request.contextPath}/admin/templates/screen/${template.screen.id}';
            } else alert('Error saving layout');
        });
    }
</script>
</body>
</html>