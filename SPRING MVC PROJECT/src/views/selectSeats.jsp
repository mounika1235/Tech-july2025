<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4">
    <h2>${pageTitle}</h2>
    <form id="bookingForm" method="post" action="${pageContext.request.contextPath}/booking/hold">
    <input type="hidden" name="showId" value="${show.id}" />
        
        <!-- Use fixed grid layout -->
        <div class="seat-grid" style="display: grid; grid-template-columns: repeat(10, 40px); gap: 6px;">
            <c:forEach var="seat" items="${templateSeats}">
                <div class="seat ${seat.seatType.toLowerCase()}" data-id="${seat.id}" data-price="${seat.price}">
                    ${seat.seatCode}
                </div>
            </c:forEach>
        </div>
        
        <input type="hidden" name="seatIds" id="seatIds"/>
        <button type="submit" class="btn btn-primary mt-3">Proceed to Confirm</button>
    </form>
</div>

<script>
    const selectedSeats = new Set();
    document.querySelectorAll('.seat').forEach(seat => {
        seat.addEventListener('click', () => {
            seat.classList.toggle('selected');
            const id = seat.dataset.id;
            if (selectedSeats.has(id)) selectedSeats.delete(id);
            else selectedSeats.add(id);
            document.getElementById('seatIds').value = Array.from(selectedSeats);
        });
    });
    
    // Prevent form submission if no seats selected
    document.getElementById('bookingForm').addEventListener('submit', function(e) {
        if (selectedSeats.size === 0) {
            e.preventDefault();
            alert('Please select at least one seat');
        }
    });
</script>

<style>
    .seat { width: 40px; height: 40px; border-radius: 4px; text-align: center; line-height: 40px; cursor: pointer; }
    .seat.selected { background-color: #28a745; color: #fff; }
    .seat.regular { background-color: #6c757d; color: #fff; }
    .seat.vip { background-color: #ffc107; color: #000; }
</style>