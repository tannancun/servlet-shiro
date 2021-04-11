<%@tag description="JavaScript信息" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script>
		$.widget.bridge('uibutton', $.ui.button)
	</script>
	<script src="asset/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="asset/chart.js/Chart.min.js"></script>
	<script src="asset/sparklines/sparkline.js"></script>
	<script src="asset/jquery-knob/jquery.knob.min.js"></script>
	<script src="asset/moment/moment.min.js"></script>
	<script src="asset/daterangepicker/daterangepicker.js"></script>
	<script src="asset/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<script src="asset/summernote/summernote-bs4.min.js"></script>
	<script src="asset/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<script src="asset/AdminLTE/3.1.0/js/adminlte.js"></script>
	<script src="asset/AdminLTE/3.1.0/js/demo.js"></script>
	<script src="asset/datatables/jquery.dataTables.min.js"></script>
	<script src="asset/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script src="asset/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script	src="asset/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script src="asset/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script src="asset/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="asset/jszip/jszip.min.js"></script>
	<script src="asset/pdfmake/pdfmake.min.js"></script>
	<script src="asset/pdfmake/vfs_fonts.js"></script>		
	<script src="asset/datatables-buttons/js/buttons.html5.min.js"></script>
	<script src="asset/datatables-buttons/js/buttons.print.min.js"></script>
	<script src="asset/datatables-buttons/js/buttons.colVis.min.js"></script>	
	<script src="asset/jquery-validation/jquery.validate.min.js"></script>	
	<script src="asset/jquery-validation/localization/messages_zh.min.js"></script>
	<script src="asset/overhang/overhang.min.js"></script>
	<c:if test="${not empty msg}"><script type="text/javascript">$('body').overhang({type: "info", message: '${msg}'});</script></c:if>	
<jsp:doBody></jsp:doBody>	