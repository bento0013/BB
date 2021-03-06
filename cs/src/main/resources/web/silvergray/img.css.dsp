<%@ page contentType="text/css;charset=UTF-8" %> 
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %> 
<c:set var="project" value="silvergray/img"/> 
.z-detail .z-detail-img {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/row-expand.png'))}); 
}
tr.z-row .z-detail-outer {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/detail-bg.png'))});
}
ul li	{
	list-style: url(${c:encodeURL(c:cat3('~./',project,'/zweb/bullet1.gif'))}) disc
}
ul ul li	{
	list-style: url(${c:encodeURL(c:cat3('~./',project,'/zweb/bullet2.gif'))}) circle
}
ul ul ul li	{
	list-style: url(${c:encodeURL(c:cat3('~./',project,'/zweb/bullet3.gif'))}) square
}
.z-debug-domtree .z-debug-domtree-header {
	background:transparent url(${c:encodeURL(c:cat3('~./',project,'/zk/debug/hd-gray.png'))}) repeat-x 0 -1px;
}
.z-debug-domtree-close {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zk/debug/tool-btn.gif'))});
}
.z-initing {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zk/zkpowered.png'))});
}
.z-apply-loading-icon,
.z-loading-icon {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zk/progress2.gif'))});
}
.z-dd-stackup {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/spacer.gif'))});
}
.z-shadow .z-shadow-cl{
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-cl.png'))});
}
.z-shadow .z-shadow-cr{
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-cr.png'))});
}
.z-shadow .z-shadow-cm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-m.png'))});
}
.z-shadow .z-shadow-tl,
.z-shadow .z-shadow-tr{
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-tlr.png'))});
}

.z-shadow .z-shadow-bl,
.z-shadow .z-shadow-br{
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-blr.png'))});
}
span.z-drop-allow {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/drag.png'))});
}
span.z-drop-disallow {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/drag.png'))});
}
div.z-drop-cnt {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/drop-bg.gif'))});
}
.z-upload-icon {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/prgmeter.png'))});
}
.z-fileupload-add {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/fileupload.gif'))});
}
.z-fileupload-rm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/fileupload.gif'))});
}
span.z-drop-allow,
span.z-drop-disallow {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/drag.gif'))});
}
.z-splitter-hor-outer {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/splt-h-ns.png'))});
}
.z-splitter-ver-outer .z-splitter-ver-outer-td {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/splt-v-ns.png'))});
}
.z-splitter-hor {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/splt-h.png'))});
}
.z-splitter-ver {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/splt-v.png'))});
}
.z-splitter-hor-btn-l {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-l.png'))});
}
.z-splitter-hor-btn-r {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-r.png'))});
}
.z-splitter-ver-btn-t {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-t.png'))});
}
.z-splitter-ver-btn-b {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-b.png'))});
}
.z-splitter-os-hor-outer {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/splt-h-os.gif'))});
}
.z-splitter-os-ver-outer .z-splitter-os-ver-outer-td {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/splt-v-os.gif'))});
}

.z-splitter-os-hor-btn-l {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-l-os.gif'))});
}
.z-splitter-os-hor-btn-r {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-r-os.gif'))});
}
.z-splitter-os-ver-btn-t {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-t-os.gif'))});
}
.z-splitter-os-ver-btn-b {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-b-os.gif'))});
}

.z-splitter-ver-btn-l, .z-splitter-hor-btn-l {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-l.gif'))});
}
.z-splitter-ver-btn-r, .z-splitter-hor-btn-r {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-r.gif'))});
}
.z-splitter-ver-btn-t, .z-splitter-hor-btn-t {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-t.gif'))});
}
.z-splitter-ver-btn-b, .z-splitter-hor-btn-b {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-b.gif'))});
}
div.z-grid-header tr.z-columns, div.z-grid-header tr.z-auxhead {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-bg.png'))});
}
div.z-grid-header .z-column-sort div.z-column-cnt {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/sort/v_hint.gif'))});
}
div.z-grid-header .z-column-sort-asc div.z-column-cnt {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/sort/v_asc.gif'))});
}
div.z-grid-header .z-column-sort-asc, div.z-grid-header .z-column-sort-dsc {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-over.png'))});
}
div.z-grid-header .z-column-sort-dsc div.z-column-cnt {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/sort/v_dsc.gif'))});
}
tr.z-group {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/group_bg.gif'))});
}
.z-group-img {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/toggle.gif'))});
}
.z-groupfoot {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/groupfoot_bg.gif'))});
}
.z-column-btn {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/hd-btn.png'))});
}
.z-column-over {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-over.png'))});
}
.z-columns-menu-grouping .z-menu-item-img {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/menu-group.png'))});
}
.z-columns-menu-asc .z-menu-item-img {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/menu-arrowup.png'))});
}
.z-columns-menu-dsc .z-menu-item-img {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/menu-arrowdown.png'))});
}
.z-columns-menu-grouping .z-menu-item-img {
	background-image:  url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/menu-group.gif'))});
}
.z-columns-menu-asc .z-menu-item-img {
	background-image:  url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/menu-arrowup.gif'))});
}
.z-columns-menu-dsc .z-menu-item-img {
	background-image:  url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/menu-arrowdown.gif'))});
}

.z-word-wrap div.z-row-cnt, 
.z-word-wrap div.z-group-cnt,
.z-word-wrap div.z-groupfoot-cnt,
.z-word-wrap div.z-footer-cnt,
.z-word-wrap div.z-column-cnt {
	-moz-binding: url(${c:encodeURL(c:cat3('~./',project,'/zk/wordwrap.xml#wordwrap'))});
}

.z-combobox-inp {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg.gif'))}) repeat-x 0 0;
}
.z-combobox-text-invalid {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg-invalid.gif'))}) repeat-x 0 0;
}

.z-combobox .z-combobox-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/button/combobtn.gif'))});
}

.z-bandbox-inp {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg.gif'))}) repeat-x 0 0;
}
.z-bandbox-text-invalid {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg-invalid.gif'))}) repeat-x 0 0;
}

.z-bandbox .z-bandbox-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/button/bandbtn.gif'))});
}

.z-datebox-inp {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg.gif'))}) repeat-x 0 0;
}
.z-datebox-text-invalid {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg-invalid.gif'))}) repeat-x 0 0;
}

.z-datebox .z-datebox-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/button/datebtn.gif'))});
}
.z-timebox-inp {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg.gif'))}) repeat-x 0 0;
}
.z-timebox-text-invalid {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg-invalid.gif'))}) repeat-x 0 0;
}

.z-timebox .z-timebox-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/button/timebtn.gif'))});
}
.z-spinner-inp {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg.gif'))}) repeat-x 0 0;
}
.z-spinner-text-invalid {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg-invalid.gif'))}) repeat-x 0 0;
}
.z-spinner .z-spinner-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/button/timebtn.gif'))});
}

.z-spinner-readonly,
.z-timebox-readonly,
.z-datebox-readonly,
.z-bandbox-readonly,
.z-combobox-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/readonly-bg.gif'))});
}

.z-spinner-focus .z-spinner-readonly,
.z-timebox-focus .z-timebox-readonly,
.z-datebox-focus .z-datebox-readonly,
.z-bandbox-focus .z-bandbox-readonly,
.z-combobox-focus .z-combobox-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/readonly-focus-bg.gif'))});
}
.z-textbox, .z-decimalbox, .z-intbox, .z-longbox, .z-doublebox {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg.gif'))});
}
.z-textbox-text-invalid,
.z-decimalbox-text-invalid,
.z-intbox-text-invalid,
.z-longbox-text-invalid,
.z-doublebox-text-invalid {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/text-bg-invalid.gif'))});
}
.z-combobox-rounded-inp {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/combobox-rounded.gif'))});
}
.z-bandbox-rounded-inp {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/bandbox-rounded.gif'))});
}
.z-datebox-rounded-inp {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/datebox-rounded.gif'))});
}
.z-timebox-rounded-inp,
.z-spinner-rounded-inp {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/timebox-rounded.gif'))});	
}
.z-combobox-rounded .z-combobox-rounded-btn{	
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/combobox-rounded.gif'))});
}
.z-bandbox-rounded .z-bandbox-rounded-btn {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/bandbox-rounded.gif'))});
}
.z-datebox-rounded .z-datebox-rounded-btn {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/datebox-rounded.gif'))});
}
.z-timebox-rounded .z-timebox-rounded-btn,
.z-spinner-rounded .z-spinner-rounded-btn {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/timebox-rounded.gif'))});
}
.z-combobox-rounded-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/combobox-rounded.gif'))});
}
.z-bandbox-rounded-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/bandbox-rounded.gif'))});
}
.z-datebox-rounded-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/datebox-rounded.gif'))});
}
.z-timebox-rounded-readonly,
.z-spinner-rounded-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/timebox-rounded.gif'))});	
}
.z-combobox-rounded-focus .z-combobox-rounded-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/combobox-rounded.gif'))});
}
.z-bandbox-rounded-focus .z-bandbox-rounded-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/bandbox-rounded.gif'))});
}
.z-datebox-rounded-focus .z-datebox-rounded-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/datebox-rounded.gif'))});
}
.z-timebox-rounded-focus .z-timebox-rounded-readonly,
.z-spinner-rounded-focus .z-spinner-rounded-readonly {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/timebox-rounded.gif'))});	
}
.z-combobox-rounded input.z-combobox-rounded-text-invalid,
.z-bandbox-rounded input.z-bandbox-rounded-text-invalid,
.z-datebox-rounded input.z-datebox-rounded-text-invalid,
.z-timebox-rounded input.z-timebox-rounded-text-invalid,
.z-spinner-rounded input.z-spinner-rounded-text-invalid {
	background: #FFF url(${c:encodeURL(c:cat3('~./',project,'/zul/button/redcombo-rounded.gif'))}) repeat-x 0 0;	
}
.z-combobox-rounded .z-combobox-rounded-text-invalid + i.z-combobox-rounded-btn-right-edge,
.z-bandbox-rounded .z-bandbox-rounded-text-invalid + i.z-bandbox-rounded-btn-right-edge,
.z-datebox-rounded .z-datebox-rounded-text-invalid + i.z-datebox-rounded-btn-right-edge,
.z-timebox-rounded .z-timebox-rounded-text-invalid + i.z-timebox-rounded-btn-right-edge,
.z-spinner-rounded .z-spinner-rounded-text-invalid + i.z-spinner-rounded-btn-right-edge {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/redcombo-rounded.gif'))});
}
i.z-combobox-rounded i.z-combobox-rounded-btn-right-edge-invalid,
i.z-bandbox-rounded i.z-bandbox-rounded-btn-right-edge-invalid,
i.z-datebox-rounded i.z-datebox-rounded-btn-right-edge-invalid,
i.z-timebox-rounded i.z-timebox-rounded-btn-right-edge-invalid,
i.z-spinner-rounded i.z-spinner-rounded-btn-right-edge-invalid {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/button/redcombo-rounded.gif'))});
}
.z-arrow-d {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowD.png'))});
}
.z-arrow-l {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowL.png'))});
}
.z-arrow-ld {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowLD.png'))});
}
.z-arrow-lu {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowLU.png'))});
}
.z-arrow-rd {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowRD.png'))});
}
.z-arrow-ru {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowRU.png'))});
}
.z-arrow-r {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowR.png'))});
}
.z-arrow-u {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowU.png'))});
}
.z-errbox-close {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/errbox/close.gif'))});
}
.z-errbox-close-over {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/errbox/close-over.gif'))});
}
.z-errbox.z-popup .z-popup-tl,
.z-errbox.z-popup .z-popup-tr,
.z-errbox.z-popup .z-popup-bl,
.z-errbox.z-popup .z-popup-br {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/errbox/pp-corner.png'))});
}
.z-errbox.z-popup .z-popup-cm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/errbox/pp-cm.png'))});
}
.z-errbox.z-popup .z-popup-cl,
.z-errbox.z-popup .z-popup-cr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/errbox/pp-clr.png'))});
}

.z-arrow-d {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowD.gif'))});
}
.z-arrow-l {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowL.gif'))});
}
.z-arrow-ld {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowLD.gif'))});
}
.z-arrow-lu {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowLU.gif'))});
}
.z-arrow-rd {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowRD.gif'))});
}
.z-arrow-ru {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowRU.gif'))});
}
.z-arrow-r {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowR.gif'))});
}
.z-arrow-u {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/arrowU.gif'))});
}
.z-errbox.z-popup .z-popup-tl,
.z-errbox.z-popup .z-popup-tr,
.z-errbox.z-popup .z-popup-bl,
.z-errbox.z-popup .z-popup-br {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/errbox/pp-corner.gif'))});
}
.z-errbox.z-popup .z-popup-cm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/errbox/pp-cm.gif'))});
}
.z-errbox.z-popup .z-popup-cl,
.z-errbox.z-popup .z-popup-cr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/errbox/pp-clr.gif'))});
}

.z-slider-sphere-hor,
.z-slider-scale,
.z-slider-hor {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-bg.png'))});
}
.z-slider-sphere-hor-center,
.z-slider-scale-center,
.z-slider-hor-center {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-bg.png'))});
}
.z-slider-sphere-hor-btn,
.z-slider-scale-btn,
.z-slider-hor-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-square.png'))});
}
.z-slider-scale-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-scale.gif'))});
}

.z-slider-scale-tick {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/ticks.gif'))});
}

.z-slider-sphere-ver,
.z-slider-ver {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-bg-ver.png'))});
}

.z-slider-sphere-ver-center,
.z-slider-ver-center {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-bg-ver.png'))});
}

.z-slider-sphere-ver-btn,
.z-slider-ver-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-v-square.png'))});
}
.z-slider-sphere-hor-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-circle.png'))});
}

.z-slider-sphere-ver-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-v-circle.png'))});
}

.z-slider-sphere-hor,
.z-slider-scale,
.z-slider-hor,
.z-slider-sphere-hor-center,
.z-slider-scale-center,
.z-slider-hor-center {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-bg.gif'))});
}
.z-slider-sphere-hor-btn,
.z-slider-scale-btn,
.z-slider-hor-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-square.gif'))});
}
.z-slider-scale-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-scale.gif'))});
}
.z-slider-sphere-ver,
.z-slider-ver,
.z-slider-sphere-ver-center,
.z-slider-ver-center {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-bg-ver.gif'))});
}

.z-slider-sphere-ver-btn,
.z-slider-ver-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-v-square.gif'))});
}
.z-slider-sphere-hor-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-circle.gif'))});
}
.z-slider-sphere-ver-btn {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/slider/slider-v-circle.gif'))});
}
.z-east-splt,
.z-west-splt,
.z-north-splt,
.z-south-splt {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/splt-h.png'))});
}
.z-north-splt,
.z-south-splt {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/splt-v.png'))});
}

.z-east-splt-btn {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-r.gif'))});
}
.z-west-splt-btn {	
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-l.gif'))});
}
.z-north-splt-btn {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-t.gif'))});
}
.z-south-splt-btn {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/splt/colps-b.gif'))});
}

.z-borderlayout-icon {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/layout/borderlayout-btn.png'))});
}

.z-west-header,
.z-center-header,
.z-east-header,
.z-north-header,
.z-south-header {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/layout/borderlayout-hm.png'))});
}

.z-borderlayout-icon {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/layout/borderlayout-btn.gif'))});
}

.z-menubar-hor
{
    padding-top : 0px;
    padding-bottom : 0px;
}

.z-menubar-hor,.z-menubar-ver {
    background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/bar-bg.png'))});
    background: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/bar-bg.png'))});

}

.z-menu-body .z-menu-inner-m div {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/btn-arrow.gif'))});
}

.z-menu-body-over .z-menu-inner-l,
.z-menu-body-seld .z-menu-inner-l,
.z-menu-item-body-over .z-menu-item-inner-l {
        background-position: 0px 0px;/*add by bento*/
        background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/active_menu_left.png'))});
	/*background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/menu-btn.png'))});*/
}

/*Add by Thanasit*/
.z-menu-popup-cnt .z-menu-item .z-menu-item-img
{
    width:0px;
    min-height:0px;
    margin-right:0px;
}

/*Add by Thanasit*/
.z-menu-popup-cnt .z-menu-item A.z-menu-item-cnt
{
    padding-right:10px;
}

/*Add by Thanasit*/
.z-menu-popup-cnt .z-menu .z-menu-img
{
    width:0px;
    margin-right:0px;
}

/* start Bento*/
.z-menubar-hor .z-menu-body .z-menu-inner-l
{
    width:10px;
    height:25px;
}

.z-menubar-hor .z-menu-body .z-menu-inner-r
{
    width:10px;
    height:25px;
}

.z-menubar-hor .z-menu-body .z-menu-inner-m
{
    height:25px;
}


.z-menubar-hor .z-menu-item-body .z-menu-item-inner-r
{
    width:10px;
    height:25px;
}

.z-menubar-hor .z-menu-item-body .z-menu-item-inner-l
{
    width:10px;
    height:25px;
}

.z-menubar-hor .z-menu-item-body .z-menu-item-inner-m
{
    height:25px;
}
/* finish Bento*/

.z-menu-body-over .z-menu-inner-r,
.z-menu-body-seld .z-menu-inner-r,
.z-menu-item-body-over .z-menu-item-inner-r {
        background-position: 0px 0px;/*add by bento*/
        background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/active_menu_right.png'))});
	/*background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/menu-btn.png'))});*/
}
.z-menu-body-over .z-menu-inner-m,
.z-menu-body-seld .z-menu-inner-m,
.z-menu-item-body-over .z-menu-item-inner-m {
        background-position: 0px 0px;/*add by bento*/
        background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/active_menu_center.png'))});
	/*background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/menu-btn.png'))});*/
}
.z-menu-popup {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/pp-bg.png'))});
}

.z-menu-popup-cnt .z-menu .z-menu-cnt-img {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/arrow.gif'))});
}

.z-menu-popup-cnt .z-menu-item-cnt-ck .z-menu-item-img {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/checked.gif'))});
}

.z-menu-popup-cnt .z-menu-item-cnt-unck .z-menu-item-img {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/unchecked.gif'))});
}

.z-menu-popup-cnt .z-menu-over,
.z-menu-popup-cnt .z-menu-item-over{
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/menu/item-over.png'))});
}

.z-menubar-hor .z-menu-separator {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zweb/dot.gif'))});
}
.z-menubar-ver .z-menu-separator {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zweb/dot.gif'))});
}
.z-frozen {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/bar-bg.png'))});
}
.z-paging-os .z-paging-os-cnt {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-bg.png'))});
}
.z-paging-os .z-paging-os-seld {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/paging-os-seld.gif'))});
}

.z-paging {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/bar-bg.png'))});
}

.z-paging .z-paging-sep {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/paging/pg-split.gif'))});
}
.z-paging-btn .z-paging-next {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/paging/pg-btn.png'))});
}
.z-paging-btn .z-paging-prev {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/paging/pg-btn.png'))});
}
.z-paging-btn .z-paging-last {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/paging/pg-btn.png'))});
}
.z-paging-btn .z-paging-first {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/paging/pg-btn.png'))});
}

.z-paging-btn .z-paging-next,
.z-paging-btn .z-paging-prev,
.z-paging-btn .z-paging-last,
.z-paging-btn .z-paging-first {	
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/paging/pg-btn.gif'))}) !important;
}

div.z-listbox-header tr.z-listhead, div.z-listbox-header tr.z-auxhead {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-bg.png'))});
}
div.z-listbox-header th.z-listheader-sort div.z-listheader-cnt {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/sort/v_hint.gif'))});
}
div.z-listbox-header th.z-listheader-sort-asc div.z-listheader-cnt {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/sort/v_asc.gif'))});
}
div.z-listbox-header th.z-listheader-sort-dsc div.z-listheader-cnt {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/sort/v_dsc.gif'))});
}
tr.z-listitem td.z-listitem-focus {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/focusd.png'))});
}
tr.z-listgroup{
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/group_bg.gif'))});
}
.z-listgroup-img {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/toggle.gif'))});
}
.z-listgroupfoot{
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/groupfoot_bg.gif'))});
}
tr.z-listitem td.z-listitem-focus {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/focusd.gif'))});
}

.z-word-wrap div.z-listcell-cnt,
.z-word-wrap div.z-listfooter-cnt,
.z-word-wrap div.z-listheader-cnt {
	-moz-binding: url(${c:encodeURL(c:cat3('~./',project,'/zk/wordwrap.xml#wordwrap'))});
}
div.z-tree-header tr.z-treecols, div.z-tree-header tr.z-auxhead {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-bg.png'))});
}
span.z-tree-root-open, span.z-tree-tee-open, span.z-tree-last-open,
span.z-tree-root-close, span.z-tree-tee-close, span.z-tree-last-close {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/toggle.gif'))});
}
tr.z-treerow td.z-treerow-focus {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/focusd.png'))});
}
div.z-dottree-header tr.z-treecols, div.z-tree-header tr.z-auxhead  {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-bg.png'))});
}
span.z-dottree-root-open, span.z-dottree-root-close{
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/dot-toggle.gif'))});
}
span.z-dottree-tee-open {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/tee-open.gif'))});
}
span.z-dottree-tee-close {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/tee-close.gif'))});
}
span.z-dottree-last-open {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/tee-last-open.gif'))});
}
span.z-dottree-last-close {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/tee-last-close.gif'))});
}
span.z-dottree-tee {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/tee.gif'))});
}
span.z-dottree-vbar {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/tee-vbar.gif'))});
}
span.z-dottree-last {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/tee-last.gif'))});
}
div.z-filetree-header tr.z-treecols, div.z-tree-header tr.z-auxhead  {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-bg.png'))});
}
span.z-filetree-ico,span.z-filetree-firstspacer {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/folder-toggle.gif'))});
}
span.z-filetree-tee, span.z-filetree-last {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/entity.gif'))});
}
div.z-vfiletree-header tr.z-treecols, div.z-tree-header tr.z-auxhead  {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/grid/column-bg.png'))});
}
span.z-vfiletree-ico,span.z-vfiletree-firstspacer {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/vfolder-toggle.png'))});
}
span.z-vfiletree-tee, span.z-vfiletree-last {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/ventity.png'))});
}
tr.z-treerow td.z-treerow-focus {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/focusd.gif'))});
}
span.z-vfiletree-ico, span.z-vfiletree-firstspacer {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/vfolder-toggle.gif'))});
}
span.z-vfiletree-tee, span.z-vfiletree-last {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tree/ventity.gif'))});
}

.z-word-wrap div.z-treecell-cnt,
.z-word-wrap div.z-treefooter-cnt, 
.z-word-wrap div.z-treecol-cnt {
	-moz-binding: url(${c:encodeURL(c:cat3('~./',project,'/zk/wordwrap.xml#wordwrap'))});
}
.z-toolbar-tabs-outer, .z-toolbar-tabs {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tabs-bg.png'))});
}
.z-tabs-scroll .z-tabs-cnt {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tabs-bg.png'))});
}
.z-tab-close {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-close.gif'))});
}
.z-tab-hl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-corner.png'))});
}
.z-tab-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-corner.png'))});
}
.z-tab-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-hm.png'))});
}
.z-tabs-scroll .z-tabs-right-scroll {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/scroll-r.png'))});
}
.z-tabs-scroll .z-tabs-left-scroll {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/scroll-l.png'))});
}
.z-tabs-ver-scroll .z-tabs-ver-header {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tabs-v-bg.png'))});
}
.z-tab-ver-close {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-close.gif'))});
}
.z-tab-ver-hl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-v-corner.png'))});
}

.z-tab-ver-hl .z-tab-ver-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-v-corner.png'))});
}
.z-tab-ver .z-tab-ver-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-v-hm.png'))});
}
.z-tabs-ver-up-scroll {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/scroll-u.png'))});
}
.z-tabs-ver-down-scroll {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/scroll-d.png'))});
}
.z-tab-accordion-tl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/accd-corner.png'))});
}
.z-tab-accordion-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/accd-corner.png'))});
}
.z-tab-accordion-hl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/accd-hl.png'))});
}
.z-tab-accordion-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/accd-hr.png'))});
}
.z-tab-accordion-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/accd-hm.png'))});
}
.z-tab-accordion-close {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/close-off.gif'))});
}
.z-tab-accordion-close-over,
.z-tab-accordion .z-tab-accordion-close:hover {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/accd-close-on.gif'))});
}
.z-tab-accordion-disd .z-tab-accordion-close:hover,
.z-tab-accordion-disd-seld .z-tab-accordion-close:hover {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/close-off.gif'))});
}
.z-tab-accordion-lite-tl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/lite-all.png'))});
}
.z-tab-accordion-lite-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/lite-all.png'))});
}
.z-tab-accordion-lite-tm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/lite-all.png'))});
}
.z-tab-accordion-lite-close {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/close-off.gif'))});
}
.z-tab-accordion-lite-close-over,
.z-tab-accordion-lite .z-tab-accordion-lite-close:hover {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/lite-close-on.gif'))});
}
.z-tab-accordion-lite-disd .z-tab-accordion-lite-close:hover,
.z-tab-accordion-lite-disd-seld .z-tab-accordion-lite-close:hover {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/close-off.gif'))});
}

.z-tab-hl,
.z-tab-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-corner.gif'))});
}
.z-tab-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-hm.gif'))});
}
.z-tab-ver-hl,
.z-tab-ver-hl .z-tab-ver-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-v-corner.gif'))});
}
.z-tab-ver .z-tab-ver-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/tab-v-hm.png'))});
}
.z-tab-accordion-tl,
.z-tab-accordion-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/tab/accd-corner.gif'))});
}
.z-button .z-button-tl, .z-button .z-button-tr, .z-button .z-button-bl, .z-button .z-button-br{
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/button/btn-corner.gif'))});
}
.z-button .z-button-tm, .z-button .z-button-bm  {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/button/btn-x.gif'))});
}
.z-button .z-button-cl, .z-button .z-button-cr {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/button/btn-y.gif'))});
}
.z-button .z-button-cm {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/button/btn-ctr.gif'))});
}

.z-groupbox-tl {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-corner.png'))});
}
.z-groupbox-tr{
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-corner.png'))});
}

.z-groupbox-hl {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-hl.png'))});
}
.z-groupbox-hr {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-hr.png'))});
}
.z-groupbox-hm {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-hm.png'))});
}

.z-groupbox-bl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zweb/shdlf.gif'))});
}
.z-groupbox-br {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zweb/shdrg.gif'))});
}
.z-groupbox-bm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zweb/shdmd.gif'))});
}

.z-groupbox-tl {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-corner.gif'))});
}
.z-groupbox-tr{
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-corner.gif'))});
}

.z-groupbox-hl {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-hl.gif'))});
}
.z-groupbox-hr {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-hr.gif'))});
}
.z-groupbox-hm {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/groupbox/groupbox-hm.gif'))});
}
.z-popup .z-popup-tl {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-corner.png'))});
}
.z-popup .z-popup-tr {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-corner.png'))});
}
.z-popup .z-popup-cm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-cm.png'))});
}
.z-popup .z-popup-cl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-clr.png'))});
}
.z-popup .z-popup-cr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-clr.png'))});
}

.z-popup .z-popup-bl {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-corner.png'))});
}
.z-popup .z-popup-br {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-corner.png'))});
}

.z-popup .z-popup-tl,
.z-popup .z-popup-tr,
.z-popup .z-popup-bl,
.z-popup .z-popup-br {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-corner.gif'))});
}
.z-popup .z-popup-cl,
.z-popup .z-popup-cr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-clr.gif'))});
}
.z-popup .z-popup-cm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/popup/pp-cm.gif'))});
}

div.z-progressmeter {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zk/prgmeter_bg.png'))});
}
span.z-progressmeter-img {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zk/prgmeter.png'))});
}
.z-separator-hor-bar {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zweb/dot.gif'))});
}
.z-separator-ver-bar {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zweb/dot.gif'))});
}

.z-toolbar {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/bar-bg.png'))});
}
.z-panel-tl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-corner.png'))});
}
.z-panel-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-corner.png'))});
}
.z-panel-hl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-hl.png'))});
}
.z-panel-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-hr.png'))});
}
.z-panel-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-hm.png'))});
}
.z-panel-cl,
.z-panel-fl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-clr.png'))});
}
.z-panel-cr,
.z-panel-fr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-clr.png'))});
}
.z-panel-bl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-corner.png'))});
}
.z-panel-br {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-corner.png'))});
}
.z-panel-header {
        height:17px;
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-hm.png'))});
}
.z-panel-icon {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/btn.gif'))});
}

.z-panel-tl,
.z-panel-tr,
.z-panel-bl,
.z-panel-br {
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/panel-corner.gif'))});
}
.z-window-embedded-tl,
.z-window-modal-tl,
.z-window-highlighted-tl,
.z-window-overlapped-tl,
.z-window-popup-tl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-corner.png'))});
}
.z-window-embedded-tl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-corner.png'))});
}
.z-window-popup-tl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-corner.png'))});
}
.z-window-embedded-tr,
.z-window-modal-tr,
.z-window-highlighted-tr,
.z-window-overlapped-tr,
.z-window-popup-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-corner.png'))});
}
.z-window-embedded-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-corner.png'))});
}
.z-window-popup-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-corner.png'))});
}
.z-window-embedded-hl,
.z-window-modal-hl,
.z-window-highlighted-hl,
.z-window-overlapped-hl,
.z-window-popup-hl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-hl.png'))});
}
.z-window-embedded-hl{
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-hl.png'))});
}
.z-window-popup-hl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-hl.png'))});
}
.z-window-embedded-hr,
.z-window-modal-hr,
.z-window-highlighted-hr,
.z-window-overlapped-hr,
 .z-window-popup-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-hr.png'))});
}
.z-window-embedded-hr, .z-window-embedded-hr-noborder {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-hr.png'))});
}
.z-window-popup-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-hr.png'))});
}
.z-window-embedded-hm,
.z-window-modal-hm,
.z-window-highlighted-hm,
.z-window-overlapped-hm,
.z-window-popup-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-hm.png'))});
}
.z-window-embedded-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-hm.png'))});
}
.z-window-popup-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-hm.png'))});
}
.z-window-modal-cl,
.z-window-highlighted-cl,
.z-window-overlapped-cl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-clr.png'))});
}
.z-window-modal-cr,
.z-window-highlighted-cr,
.z-window-overlapped-cr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-clr.png'))});
}
.z-window-modal-bl,
.z-window-highlighted-bl,
.z-window-overlapped-bl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-corner.png'))});
}
.z-window-modal-br,
.z-window-highlighted-br,
.z-window-overlapped-br {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-corner.png'))});
}
.z-window-embedded-icon {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/ebd-btn.gif'))});
}
.z-window-modal-icon,
.z-window-highlighted-icon,
.z-window-overlapped-icon {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/ol-btn.gif'))});
}
.z-window-popup-icon {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/pop-btn.gif'))});
}
.z-msgbox-question {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/question-btn.png'))});
}
.z-msgbox-exclamation {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/warning-btn.png'))});
}
.z-msgbox-information {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/info-btn.png'))});
}
.z-msgbox-error {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/stop-btn.png'))});
}

.z-msgbox-success {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/stop-btn.png'))});
}

.z-window-modal-tl,
.z-window-modal-tr,
.z-window-modal-bl,
.z-window-modal-br,
.z-window-overlapped-tl,
.z-window-overlapped-tr,
.z-window-overlapped-bl,
.z-window-overlapped-br,
.z-window-highlighted-tl,
.z-window-highlighted-tr,
.z-window-highlighted-bl,
.z-window-highlighted-br {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-corner.gif'))});
}
.z-window-embedded-tl,
.z-window-embedded-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-corner.gif'))});
}
.z-window-popup-tl,
.z-window-popup-tr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-corner.gif'))});
}
.z-window-modal-hr,
.z-window-highlighted-hr,
.z-window-overlapped-hr,
.z-window-popup-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-hr.gif'))});
}
.z-window-embedded-hr,
.z-window-embedded-hr-noborder {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-hr.gif'))});
}
.z-window-popup-hr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-hr.gif'))});
}
.z-window-modal-hl,
.z-window-highlighted-hl,
.z-window-overlapped-hl,
.z-window-popup-hl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-hl.gif'))});
}
.z-window-embedded-hl,
.z-window-embedded-hl-noborder {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-hl.gif'))});
}
.z-window-popup-hl {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-hl.gif'))});
}
.z-window-modal-hm,
.z-window-highlighted-hm,
.z-window-overlapped-hm,
.z-window-popup-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-hm.gif'))});
}
.z-window-embedded-hm,
.z-window-embedded-hm-noborder {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-hm.gif'))});
}
.z-window-popup-hm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-pop-hm.gif'))});
}
.z-window-modal-cl,
.z-window-highlighted-cl,
.z-window-overlapped-cl,
.z-window-modal-cr,
.z-window-highlighted-cr,
.z-window-overlapped-cr {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/wnd/wnd-ol-clr.gif'))});
}

.z-msgbox-question {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/question-btn.gif'))});
}
.z-msgbox-exclamation {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/warning-btn.gif'))});
}
.z-msgbox-information {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/info-btn.gif'))});
}
.z-msgbox-error {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/msgbox/stop-btn.gif'))});
}
.z-debug-domtree .z-debug-domtree-header {
	background:transparent url(${c:encodeURL(c:cat3('~./',project,'/zk/debug/hd-gray.png'))}) repeat-x 0 -1px;
}
.z-debug-domtree-close {
	background-image : url(${c:encodeURL(c:cat3('~./',project,'/zk/debug/tool-btn.gif'))});
}
.z-initing {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zk/zkpowered.png'))});
}
.z-apply-loading-icon,
.z-loading-icon {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zk/progress2.gif'))});
}
.z-dd-stackup {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/spacer.gif'))});
}
.z-shadow .z-shadow-cl{
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-cl.png'))});
}
.z-shadow .z-shadow-cr{
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-cr.png'))});
}
.z-shadow .z-shadow-cm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-m.png'))});
}
.z-shadow .z-shadow-tl,
.z-shadow .z-shadow-tr{
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-tlr.png'))});
}

.z-shadow .z-shadow-bl,
.z-shadow .z-shadow-br{
	background-image:url(${c:encodeURL(c:cat3('~./',project,'/zul/shadow-blr.png'))});
}
span.z-drop-allow {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/drag.png'))});
}
span.z-drop-disallow {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/drag.png'))});
}
div.z-drop-cnt {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/drop-bg.gif'))});
}
.z-upload-icon {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/prgmeter.png'))});
}
.z-fileupload-add {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/fileupload.gif'))});
}
.z-fileupload-rm {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/fileupload.gif'))});
}
span.z-drop-allow,
span.z-drop-disallow {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/misc/drag.gif'))});
}
.z-colorpicker-arrows {	
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zkex/colorbox/colorpicker_arrows.gif'))});
}
.z-treerow-img-checkbox, .z-treerow-img-radio,
.z-listheader-img, .z-listitem-img-checkbox,
.z-listitem-img-radio, .z-listgroup-img-checkbox, .z-listgroupfoot-img {
	background-image: url(${c:encodeURL(c:cat3('~./',project,'/zul/common/check-sprite.gif'))});
}