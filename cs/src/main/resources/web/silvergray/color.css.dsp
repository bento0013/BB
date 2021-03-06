<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ page contentType="text/css;charset=UTF-8" %>

<%-- Words --%>
<c:set var="Color01" value="#403E39"/>
<%-- Menu Background --%>
<c:set var="Color02" value="#EEEDEB"/>
<%-- Group Background --%>
<c:set var="Color03" value="#F3F3F1"/>
<%-- Tab Border --%>
<c:set var="Color04" value="#ABA799"/>
<%-- Input Border --%>
<c:set var="Color05" value="#A9A597"/>
<%-- Selection --%>
<c:set var="Color06" value="#D1CFCA"/>
<%-- Zebra Color --%>
<c:set var="Color07" value="#F9F9F8"/>
<%-- Group Bottom Line --%>
<c:set var="Color08" value="#CDCDCD"/>
<%-- Hover+Select--%>
<c:set var="Color09" value="#B2AEA6"/>
<%-- Hover --%>
<c:set var="Color10" value="#D6D6D6"/>
<%-- Menu --%>
<c:set var="Color11" value="#FFFFFF"/>
/*Common*/
.z-dragged {
	background: ${Color09};
}
.z-drag-over {
	background: ${Color09};
}
div.z-drop-ghost {
	border-color:${Color04};
}
.z-loading {
	background-color:${Color04};
	border-color:${Color04};
}
div.z-progressmeter {
	border-color:${Color04};
}
.z-loading-indicator {
	border-color:${Color04};
	color:${Color01};
}
/* input boxes */
.z-textbox, .z-decimalbox, .z-intbox, .z-longbox, .z-doublebox {
	border-color : ${Color05};
}
.z-combobox-inp ,.z-spinner-inp,.z-datebox-inp,.z-timebox-inp ,.z-bandbox-inp {
	border-color : ${Color05};
}
.z-combobox .z-combobox-btn,
.z-spinner .z-spinner-btn,
.z-datebox .z-datebox-btn,
.z-timebox .z-timebox-btn ,
.z-bandbox .z-bandbox-btn {
	border-color : ${Color05};
}
.z-textbox-focus, .z-textbox-focus input,
.z-decimalbox-focus, .z-decimalbox-focus input,
.z-intbox-focus, .z-intbox-focus input,
.z-longbox-focus, .z-longbox-focus input,
.z-doublebox-focus, .z-doublebox-focus input {
	border-color : ${Color04};
}
.z-combobox-focus .z-combobox-inp,
.z-spinner-focus .z-spinner-inp,
.z-datebox-focus .z-datebox-inp,
.z-timebox-focus .z-timebox-inp ,
.z-bandbox-focus .z-bandbox-inp  {
	border-color : ${Color04};
}
.z-combobox-focus .z-combobox-btn,
.z-spinner-focus .z-spinner-btn,
.z-datebox-focus .z-datebox-btn,
.z-timebox-focus .z-timebox-btn ,
.z-bandbox-focus .z-bandbox-btn {
	border-color : ${Color04};
}
.z-combobox-rounded-pp ,.z-bandbox-rounded-pp,
.z-combobox-pp ,.z-bandbox-pp {
	border-color:${Color04};
}
.z-combobox-rounded-pp .z-comboitem-over,
.z-combobox-pp .z-comboitem-over {
	background-color: ${Color10};
}
.z-combobox-rounded-pp .z-comboitem-seld,
.z-combobox-pp .z-comboitem-seld {
	background-color:${Color06};
}
.z-combobox-rounded-pp .z-comboitem-over-seld,
.z-combobox-pp .z-comboitem-over-seld {
	background-color:${Color09};
}
.z-combobox-text-invalid,
.z-bandbox-text-invalid,
.z-datebox-text-invalid,
.z-timebox-text-invalid,
.z-spinner-text-invalid {
	border: 1px solid #DD7870;
}
/*borderlayout*/
.z-west-header, .z-center-header, .z-east-header, .z-north-header, .z-south-header {
	color : ${Color01};
}
.z-east-colpsd, .z-west-colpsd, .z-south-colpsd, .z-north-colpsd {
	background-color : ${Color07};
	border-color : ${Color04};
}
.z-west-header, .z-center-header, .z-east-header, .z-north-header, .z-south-header {
	border-color : ${Color04};
}
.z-west, .z-center, .z-east, .z-north, .z-south {
	border-color : ${Color04};
}
.z-borderlayout {
	background-color:${Color06};
}
/*calendar & datebox*/
.z-calendar-calyear,
.z-datebox-pp .z-datebox-calyear {
	background-color: ${Color02};
	border-color: ${Color07} ${Color04} ${Color04} ${Color07};
}
.z-calendar-calday,
.z-datebox-calday {
	border-color: ${Color07};
}
.z-calendar-caldow td,
.z-datebox-caldow td {
	color: ${Color01};
	background-color: ${Color02};
}
.z-calendar-calmon td, .z-calendar-calday td, .z-calendar-calday td a, .z-calendar-calday td a:visited,
.z-datebox-calmon td, .z-datebox-calday td, .z-datebox-calday td a, .z-datebox-calday td a:visited {
	color: ${Color01};
}
.z-calendar-calyear td.z-calendar-seld,
.z-calendar-calmon td.z-calendar-seld,
.z-calendar-calday td.z-calendar-seld {
	background-color: ${Color06};
	border-color: ${Color04};
}
.z-calendar-calyear .z-calendar-over,
.z-calendar-calmon .z-calendar-over,
.z-calendar-caldayrow .z-calendar-over {
	background-color: ${Color10};
}
.z-calendar td.z-calendar-over-seld,
.z-datebox-calmon td.z-datebox-over-seld,
.z-datebox-calday td.z-datebox-over-seld {
	background-color: ${Color09};
}
/*groupbox*/
.z-groupbox-tl,
.z-groupbox-hl {
	border-color : ${Color04};
}
.z-groupbox-cnt {
	border-color : ${Color04};
}
/*Menu*/
.z-menubar-hor, .z-menubar-ver {
 
}
.z-menu-popup-cnt .z-menu-item-over, .z-menu-popup-cnt .z-menu-over {
	border-color : ${Color06};
	background-color:${Color07};
}
.z-menu-popup {
	border-color : ${Color04};
}
.z-menu-separator-inner {
	background-color:${Color08};
}
.z-menu-item-body .z-menu-item-inner-m .z-menu-item-btn, .z-menu-btn{
	color : ${Color11};
	font-weight:bold;
}
.z-menu-body-over .z-menu-inner-m .z-menu-btn, 
.z-menu-body-seld .z-menu-inner-m .z-menu-btn, 
.z-menu-item-body-over .z-menu-item-inner-m .z-menu-btn{
	color : ${Color01};
	font-weight:bold;
}
.z-menu-item-body-over .z-menu-item-inner-m .z-menu-item-btn{
	color : ${Color01};
	font-weight:bold;
}
/*tree*/
div.z-tree, div.z-dottree, div.z-filetree, div.z-vfiletree {
	background-color : ${Color07};
	border-color :${Color04};
}
tr.z-treerow-seld {
	background-color : ${Color06};
}
tr.z-treerow-over{
	background-color : ${Color10};
}
tr.z-treerow-over-seld {
	background-color : ${Color09} ;
}
div.z-tree-header th.z-tree-col, div.z-tree-header th.z-auxheader,
div.z-dottree-header th, div.z-filetree-header th, div.z-vfiletree-header th {
	border-color: ${Color07} ${Color04} ${Color04} ${Color07};
}
/*listbox*/
tr.z-listbox-odd {
	background-color : ${Color07};
}
tr.z-listitem-seld {
	background-color : ${Color06};
}
tr.z-listitem-over {
	background-color : ${Color10};
}
tr.z-listitem-over-seld {
	background-color : ${Color09};
}
div.z-listbox-header th.z-listheader, div.z-listbox-header th.z-auxheader {
	border-color: ${Color07} ${Color04} ${Color04} ${Color07};
}
div.z-listbox {
	background-color : ${Color06};
	border-color : ${Color04};
}
div.z-listbox-footer {
	background-color : ${Color06};
	border-color : ${Color04};
}
div.z-listbox-pgi-b, div.z-tree-pgi-b, div.z-grid-pgi-b  {
	border-color : ${Color08};
}
/*grid*/
tr.cells td {
	border-bottom-color:${Color04};
}
div.z-grid {
	background-color:${Color07};
	border-color:${Color04};
}
tr.z-grid-odd td.z-row-inner, tr.z-grid-odd {
	background-color : ${Color07}
}
div.z-grid-header th.z-column, div.z-grid-header th.z-auxheader {
	border-color: ${Color07} ${Color04} ${Color04} ${Color07};
}
tr.z-row td.z-row-inner {
	border-color: ${Color07} ${Color04} ${Color04} ${Color07};
}
div.z-grid-footer {
	background-color : ${Color06};
	border-top-color: ${Color04};
}
/*group*/
td.z-list-group-inner div.z-list-cell-cnt {
	color : ${Color01};
}
td.z-list-group-inner {
	border-top-color: ${Color04};
	border-bottom-color:${Color08};
}
td.z-list-group-foot-inner div.z-list-cell-cnt {
	color : ${Color01};
}
tr.z-list-group {
	background-color: ${Color03};
}
tr.z-group {
	background-color: ${Color03};
}
td.z-group-inner {
	border-top-color: ${Color04};
	border-bottom-color:${Color08};
}
.z-group-inner .z-group-cnt .z-label, .z-group-inner .z-group-cnt {
	color : ${Color01};
}
.z-groupfoot-inner .z-groupfoot-cnt .z-label, .z-groupfoot-inner .z-groupfoot-cnt {
	color : ${Color01};
}
/*paging*/
.z-paging {
	border-color : ${Color04};
}
.z-paging-inp {
	border-color : ${Color05};
}
.z-paging-os .z-paging-os-cnt {
	background-color: ${Color09};
	border-color : ${Color04};
	color: ${Color01};
}
.z-paging-os .z-paging-os-seld:hover {
	color: white;
}
/*panel*/
.z-panel-hl {
	border-color : ${Color04};
}
.z-panel-noborder .z-panel-header.z-panel-header-noborder ,
.z-panel-header, .z-panel-hm .z-panel-header {
	color : ${Color01};
	border-color : ${Color04};
}
.z-panel-children {
	border-color : ${Color04};
}
.z-panel-cm {
	background-color :${Color03};
}
.z-panel-cm .z-panel-children,
.z-panel-cm .z-panel-children-noheader {
	border-color : ${Color04};
}
.z-panel-fm {
	background-color:${Color07} ;
}
.z-panel-move-ghost {
	background-color: ${Color06};
}
.z-panel-move-ghost ul {
	border-color: ${Color04};
	background-color: ${Color06};
}
.z-panel-move-block {
	border-color: ${Color04};
}
.z-panel .z-panel-body  .z-panel-top div.z-toolbar,
.z-panel .z-panel-body  .z-panel-btm div.z-toolbar,
.z-panel .z-panel-body  .z-panel-btm2 div.z-toolbar,
.z-panel-noborder .z-panel-body  .z-panel-top div.z-toolbar,
.z-panel-noborder .z-panel-body  .z-panel-btm div.z-toolbar,
.z-panel-noborder .z-panel-body  .z-panel-btm2 div.z-toolbar {
	border-color: ${Color04};
}
/*popup*/
.z-popup .z-popup-cm {
	background-color:${Color03} ;
}
/*tabbox*/
.z-tabs-scroll {
	background-color : ${Color07};
	border-color : ${Color04};
}
.z-tabs .z-tabs-space {
	background-color : ${Color07};
	border-color: ${Color04};
}
.z-tabs .z-tabs-cnt {
	border-color:${Color04};
}
.z-tabpanel{
	border-color:${Color04};
}
.z-tabbox-ver .z-tabpanels-ver {
	border-color:${Color04};
}
.z-tabs-ver-scroll {
	border-color:${Color04};
}
.z-tabs-ver .z-tabs-ver-cnt {
	border-color:${Color04};
}
.z-tabs-ver-space {
	background-color : ${Color07};
	border-color: ${Color04};
}
.z-tab .z-tab-text,
.z-tab .z-tab-hl:hover .z-tab-text {
	color : ${Color01};
}
.z-tab-seld .z-tab-text {
	color : ${Color01};
}
.z-tab .z-tab-body:hover .z-tab-text {
	color : ${Color01};
}
.z-tab-ver .z-tab-ver-text {
	color : ${Color01};
}
.z-tab-ver-seld .z-tab-ver-text {
	color : ${Color01};
}
.z-tabs-left-scroll , .z-tabs-right-scroll,
.z-tabs-ver-down-scroll, .z-tabs-ver-up-scroll {
	border-color :${Color04};
}
.z-tabbox-accordion .z-tabpanel-accordion {
	border-color :${Color04};
}
.z-tabbox-accordion-lite .z-tabpanel-accordion-lite {
	border-color:${Color04};
}
.z-tab-accordion-lite-header {
	border-color:${Color04};
}
.z-tabpanels-accordion-lite {
	border-color:${Color04};
}
/*toolbar*/
.z-toolbar, .z-toolbar-tabs-outer{
	border-color :${Color04};
}
.z-toolbar a, .z-toolbar a:visited, .z-toolbar a:hover {
	background-color : ${Color06};
	border-color : ${Color06};
}
/*window*/
.z-window-embedded-cnt, .z-window-popup-cnt {
	border-color :${Color04};
}
.z-window-embedded-tl, .z-window-embedded-tl-noborder {
	border-color : ${Color04};
}
.z-window-popup-cm, .z-window-modal-cm, .z-window-highlighted-cm, .z-window-overlapped-cm {
	border-color : ${Color01};
}
.z-window-resize-proxy {
	border-color : ${Color04};
	background-color: ${Color06};
}
.z-window-move-ghost {
	background-color : ${Color06};
}
.z-window-move-ghost dl,
.z-window-resize-faker {
	background-color : ${Color06};
	border-color : ${Color04};
}
.z-window-popup-tl {
	border-color : ${Color04};
}

/* colorpicker */
.z-colorbox {	
	border-color : ${Color04};
}

.z-colorpicker, .z-colorpicker-gradient, z-colorpicker-bar {
	border-color : ${Color04};
}

.z-colorpalette {	
	border-color : ${Color04};
}

.z-colorpalette-newcolor {
	border-color : ${Color04};
}

.z-colorpalette-hex-inp {
	border-color : ${Color04};
}

.z-colorpicker-r-inp, 
.z-colorpicker-g-inp, 
.z-colorpicker-b-inp, 
.z-colorpicker-h-inp, 
.z-colorpicker-s-inp, 
.z-colorpicker-v-inp, 
.z-colorpicker-hex-inp {
	border-color : ${Color04};
}

.z-colorpicker-ok-btn, 
.z-colorpicker-cancel-btn {
	border-color : ${Color04};
}
.z-toolbarbutton-over {
	border-top-color: ${Color04};
	border-bottom-color:${Color04};
}
.z-toolbarbutton-over .z-toolbarbutton-body {
	border-left-color: ${Color04};
	border-right-color:${Color04};
}