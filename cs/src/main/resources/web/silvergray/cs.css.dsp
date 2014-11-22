<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<c:set var="project" value=""/>
HTML {
	HEIGHT: 100%
}
BODY {
	HEIGHT: 100%
}
BODY {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; PADDING-TOP: 0px
}
IMG {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-error {
	Z-INDEX: 9999999; BORDER-BOTTOM: #940000 1px solid; POSITION: absolute; BORDER-LEFT: #bc6464 1px solid; PADDING-BOTTOM: 3px; BACKGROUND-COLOR: #ffeded; PADDING-LEFT: 3px; WIDTH: 450px; PADDING-RIGHT: 3px; DISPLAY: none; HEIGHT: 90px; BORDER-TOP: #bc6464 1px solid; TOP: 0px; BORDER-RIGHT: #940000 1px solid; PADDING-TOP: 3px; LEFT: 40%; -moz-box-shadow: 0 0 6px gray; -webkit-box-shadow: 0 0 6px gray; box-shadow: 0 0 6px gray
}
DIV.z-error .msgcnt {
	BORDER-BOTTOM: #ee7373 1px solid; BORDER-LEFT: #ee7373 1px solid; PADDING-BOTTOM: 0px; BACKGROUND-COLOR: white; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; HEIGHT: 60px; BORDER-TOP: #ee7373 1px solid; BORDER-RIGHT: #ee7373 1px solid; PADDING-TOP: 0px
}
DIV.z-error .msgs {
	PADDING-BOTTOM: 2px; PADDING-LEFT: 3px; WIDTH: 440px; PADDING-RIGHT: 3px; WORD-WRAP: break-word; HEIGHT: 60px; OVERFLOW: auto; PADDING-TOP: 2px
}
DIV.z-error .msgs .msg {
	BORDER-BOTTOM: #ff9696 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 3px
}
DIV.z-error .newmsg {
	BACKGROUND-COLOR: #ffd6d6; DISPLAY: none
}
DIV.z-error .btn {
	WIDTH: 16px; DISPLAY: inline-block; HEIGHT: 16px; COLOR: #333; MARGIN-LEFT: 10px; CURSOR: pointer
}
DIV.z-error #zk_err-p {
	CURSOR: move
}
DIV.z-error .errnum {
	PADDING-LEFT: 20px; COLOR: #c60303; FONT-WEIGHT: bold
}
.ie7 DIV.z-error .btn {
	DISPLAY: inline
}
DIV.z-error .errnum {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zk/img/error.png'))}) no-repeat -33px 2px
}
DIV.z-error .redraw {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zk/img/error.png'))}) no-repeat 0px 2px
}
DIV.z-error .close {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zk/img/error.png'))}) no-repeat -17px 2px
}
DIV.z-log {
	Z-INDEX: 99000; POSITION: absolute; TEXT-ALIGN: right; WIDTH: 50%; BOTTOM: 5px; RIGHT: 10px
}
DIV.z-log TEXTAREA {
	WIDTH: 100%
}
DIV.z-log BUTTON {
	FONT-SIZE: 11px
}
.noscript {
	Z-INDEX: 32000; POSITION: absolute; TEXT-ALIGN: center; FILTER: alpha(opacity=60); WIDTH: 100%; ZOOM: 1; BACKGROUND: #e0e1e3; HEIGHT: 100%; TOP: 0px; LEFT: 0px; opacity: .6
}
.noscript P {
	BORDER-BOTTOM: black 1px solid; FILTER: alpha(opacity=100); BORDER-LEFT: black 1px solid; PADDING-BOTTOM: 10px; MARGIN: 10% 15%; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: white; COLOR: black; BORDER-TOP: black 1px solid; FONT-WEIGHT: bold; BORDER-RIGHT: black 1px solid; PADDING-TOP: 10px; opacity: 1
}
.z-label {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-radio-cnt {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-checkbox-cnt {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
INPUT.button {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
INPUT.file {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-loading {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-modal-mask {
	POSITION: absolute; FILTER: alpha(opacity=60); WIDTH: 100%; ZOOM: 1; BACKGROUND: #e0e1e3; HEIGHT: 100%; TOP: 0px; LEFT: 0px; opacity: .6
}
.z-initing {
	Z-INDEX: 32000; POSITION: absolute; WIDTH: 60px; BOTTOM: 10px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zk/img/zkpowered.png'))}) no-repeat center 50%; HEIGHT: 60px; RIGHT: 10px
}
.z-loading {
	Z-INDEX: 31000; BORDER-BOTTOM: #83b5f7 1px solid; POSITION: absolute; BORDER-LEFT: #83b5f7 1px solid; PADDING-BOTTOM: 3px; BACKGROUND-COLOR: #6eadff; PADDING-LEFT: 3px; PADDING-RIGHT: 3px; WHITE-SPACE: nowrap; BORDER-TOP: #83b5f7 1px solid; TOP: 0px; CURSOR: wait; BORDER-RIGHT: #83b5f7 1px solid; PADDING-TOP: 3px; LEFT: 0px
}
.z-loading-indicator {
	BORDER-BOTTOM: #83b5f7 1px solid; BORDER-LEFT: #83b5f7 1px solid; PADDING-BOTTOM: 6px; BACKGROUND-COLOR: #fff; PADDING-LEFT: 6px; PADDING-RIGHT: 6px; WHITE-SPACE: nowrap; COLOR: #102b6d; BORDER-TOP: #83b5f7 1px solid; BORDER-RIGHT: #83b5f7 1px solid; PADDING-TOP: 6px
}
.z-apply-loading-icon {
	WIDTH: 16px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zk/img/progress4.gif'))}) no-repeat center 50%; HEIGHT: 16px
}
.z-loading-icon {
	WIDTH: 16px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zk/img/progress4.gif'))}) no-repeat center 50%; HEIGHT: 16px
}
.z-renderdefer {
	WIDTH: 16px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zk/img/progress4.gif'))}) no-repeat center 50%; HEIGHT: 16px
}
.z-apply-loading-icon {
	DISPLAY: inline-block; VERTICAL-ALIGN: top
}
.z-loading-icon {
	DISPLAY: inline-block; VERTICAL-ALIGN: top
}
.z-apply-mask {
	Z-INDEX: 89000; POSITION: absolute; FILTER: alpha(opacity=60); WIDTH: 100%; ZOOM: 1; BACKGROUND: #e0e1e3; HEIGHT: 100%; TOP: 0px; LEFT: 0px; opacity: .6
}
.z-apply-loading-indicator {
	BORDER-BOTTOM: #a6c5dc 1px solid; BORDER-LEFT: #a6c5dc 1px solid; PADDING-BOTTOM: 2px; BACKGROUND-COLOR: #fff; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT: 11px Tahoma; WHITE-SPACE: nowrap; COLOR: #102b6d; BORDER-TOP: #a6c5dc 1px solid; CURSOR: wait; BORDER-RIGHT: #a6c5dc 1px solid; PADDING-TOP: 2px
}
.z-apply-loading {
	Z-INDEX: 89500; BORDER-BOTTOM: #99c6e9 1px solid; POSITION: absolute; BORDER-LEFT: #99c6e9 1px solid; PADDING-BOTTOM: 3px; BACKGROUND-COLOR: #cedfec; PADDING-LEFT: 3px; PADDING-RIGHT: 3px; WHITE-SPACE: nowrap; OVERFLOW: hidden; BORDER-TOP: #99c6e9 1px solid; CURSOR: wait; BORDER-RIGHT: #99c6e9 1px solid; PADDING-TOP: 3px
}
.z-inline-block {
	DISPLAY: inline-block; VERTICAL-ALIGN: top
}
.z-word-wrap {
	WORD-WRAP: break-word
}
.z-overflow-hidden {
	OVERFLOW: hidden
}
.z-dd-stackup {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/img/spacer.gif'))}); Z-INDEX: 16800; POSITION: absolute; WIDTH: 100%; HEIGHT: 100%; TOP: 0px; LEFT: 0px
}
.z-temp * {
	BACKGROUND-COLOR: white; COLOR: white; FONT-SIZE: 5px; TEXT-DECORATION: none
}
.z-clear {
	LINE-HEIGHT: 0; WIDTH: 0px; HEIGHT: 0px; CLEAR: both; FONT-SIZE: 0px; OVERFLOW: hidden
}
.z-shadow {
	POSITION: absolute; DISPLAY: none; OVERFLOW: hidden; TOP: 0px; LEFT: 0px
}
.z-shadow-wrapper {
	PADDING-BOTTOM: 6px; HEIGHT: 100%
}
.z-shadow .z-shadow-cl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/shadow-cl.png'))}) repeat-y 0px 0px; HEIGHT: 100%; OVERFLOW: hidden
}
.z-shadow .z-shadow-cr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/shadow-cr.png'))}) repeat-y right 50%; HEIGHT: 100%; OVERFLOW: hidden
}
.z-shadow .z-shadow-cm {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/shadow-m.png'))}) 0px 0px; HEIGHT: 100%; OVERFLOW: hidden
}
.z-shadow .z-shadow-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: no-repeat 0px top; HEIGHT: 6px; FONT-SIZE: 0px; MARGIN-RIGHT: 6px
}
.z-shadow .z-shadow-tr {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: no-repeat 0px top; HEIGHT: 6px; FONT-SIZE: 0px; MARGIN-RIGHT: 6px
}
.z-shadow .z-shadow-bl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: no-repeat 0px top; HEIGHT: 6px; FONT-SIZE: 0px; MARGIN-RIGHT: 6px
}
.z-shadow .z-shadow-br {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: no-repeat 0px top; HEIGHT: 6px; FONT-SIZE: 0px; MARGIN-RIGHT: 6px
}
.z-shadow .z-shadow-tr {
	POSITION: relative; BACKGROUND-POSITION: right -6px; MARGIN-RIGHT: -6px
}
.z-shadow .z-shadow-br {
	POSITION: relative; BACKGROUND-POSITION: right -6px; MARGIN-RIGHT: -6px
}
.z-shadow .z-shadow-tl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/shadow-tlr.png'))})
}
.z-shadow .z-shadow-tr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/shadow-tlr.png'))})
}
.z-shadow .z-shadow-bl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/shadow-blr.png'))})
}
.z-shadow .z-shadow-br {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/shadow-blr.png'))})
}
.z-dragged {
	BACKGROUND: #e0eaf0; COLOR: #888
}
.z-drag-over {
	BACKGROUND: #add2ff
}
SPAN.z-drop-allow {
	MIN-HEIGHT: 16px; WIDTH: 16px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 16px; VERTICAL-ALIGN: top
}
SPAN.z-drop-disallow {
	MIN-HEIGHT: 16px; WIDTH: 16px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 16px; VERTICAL-ALIGN: top
}
SPAN.z-drop-allow {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/drag.png'))}) no-repeat 0px -64px
}
SPAN.z-drop-disallow {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/drag.png'))}) no-repeat 0px -80px
}
DIV.z-drop-ghost {
	BORDER-BOTTOM: #6699ce 1px solid; BORDER-LEFT: #6699ce 1px solid; BORDER-TOP: #6699ce 1px solid; BORDER-RIGHT: #6699ce 1px solid
}
DIV.z-drop-cnt {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/drop-bg.gif'))}); PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; WIDTH: 120px; PADDING-RIGHT: 2px; FONT-FAMILY: Tahoma; HEIGHT: 18px; FONT-SIZE: 11px; FONT-WEIGHT: normal; PADDING-TOP: 2px
}
.z-focus-a {
	POSITION: absolute; PADDING-BOTTOM: 0px !important; LINE-HEIGHT: 0 !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; OUTLINE-STYLE: none; PADDING-LEFT: 0px !important; OUTLINE-WIDTH: 0px; WIDTH: 1px !important; PADDING-RIGHT: 0px !important; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 1px !important; FONT-SIZE: 0px !important; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px !important; TOP: 0px; PADDING-TOP: 0px !important; LEFT: 0px; -moz-outline: 0 none; -moz-user-select: text; -khtml-user-select: text
}
SPAN.z-upload {
	POSITION: relative; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 0px; PADDING-RIGHT: 0px; DISPLAY: inline-block; HEIGHT: 0px; FONT-SIZE: 0px; PADDING-TOP: 0px
}
SPAN.z-upload INPUT {
	Z-INDEX: 1; POSITION: absolute; FILTER: alpha(opacity=0); PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-SIZE: 45pt; CURSOR: pointer; PADDING-TOP: 0px; opacity: 0
}
.z-upload-icon {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/prgmeter.png'))}); OVERFLOW: hidden
}
.z-fileupload-rm {
	WIDTH: 16px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/fileupload.gif'))}) no-repeat 0px 0px; HEIGHT: 17px; CURSOR: pointer
}
.z-fileupload-progress {
	WIDTH: 300px
}
.z-fileupload-manager {
	WIDTH: 350px
}
.z-auxheader-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
.z-word-wrap .z-auxheader-cnt {
	WORD-WRAP: break-word
}
IMG {
	hspace: 0; vspace: 0
}
OPTION {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-a {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px
}
.z-a-disd {
	COLOR: #c5cacb !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
.z-a-disd:visited {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
.z-a-disd:hover {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
.z-combobox-rounded {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: inline-block; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-bandbox-rounded {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: inline-block; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-datebox-rounded {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: inline-block; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-combobox {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: inline-block; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-bandbox {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: inline-block; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-datebox {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: inline-block; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-combobox-rounded-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-bandbox-rounded-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-datebox-rounded-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-timebox-rounded-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-spinner-rounded-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-doublespinner-rounded-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-combobox-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-bandbox-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-datebox-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-timebox-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-spinner-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-doublespinner-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px 0 0 2px; -moz-border-radius: 2px 0 0 2px; -webkit-border-radius: 2px 0 0 2px
}
.z-combobox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 5px
}
.z-bandbox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 5px
}
.z-datebox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 5px
}
.z-timebox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 5px
}
.z-spinner-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 5px
}
.z-doublespinner-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 5px
}
.z-combobox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobox-rounded.gif'))})
}
.z-bandbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/bandbox-rounded.gif'))})
}
.z-datebox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/datebox-rounded.gif'))})
}
.z-timebox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-spinner-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-doublespinner-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-combobox-focus .z-combobox-inp {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-bandbox-focus .z-bandbox-inp {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-datebox-focus .z-datebox-inp {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-timebox-focus .z-timebox-inp {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-spinner-focus .z-spinner-inp {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-doublespinner-focus .z-doublespinner-inp {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-combobox .z-combobox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-bandbox .z-bandbox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-datebox .z-datebox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-timebox .z-timebox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-spinner .z-spinner-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-doublespinner .z-doublespinner-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-combobox INPUT.z-combobox-right-edge {
	BORDER-RIGHT-WIDTH: 1px !important
}
.z-bandbox INPUT.z-bandbox-right-edge {
	BORDER-RIGHT-WIDTH: 1px !important
}
.z-datebox INPUT.z-datebox-right-edge {
	BORDER-RIGHT-WIDTH: 1px !important
}
.z-timebox INPUT.z-timebox-right-edge {
	BORDER-RIGHT-WIDTH: 1px !important
}
.z-spinner INPUT.z-spinner-right-edge {
	BORDER-RIGHT-WIDTH: 1px !important
}
.z-doublespinner INPUT.z-doublespinner-right-edge {
	BORDER-RIGHT-WIDTH: 1px !important
}
.z-combobox-rounded INPUT.z-combobox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) #fff repeat-x 0px 0px
}
.z-bandbox-rounded INPUT.z-bandbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) #fff repeat-x 0px 0px
}
.z-datebox-rounded INPUT.z-datebox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) #fff repeat-x 0px 0px
}
.z-timebox-rounded INPUT.z-timebox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) #fff repeat-x 0px 0px
}
.z-spinner-rounded INPUT.z-spinner-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) #fff repeat-x 0px 0px
}
.z-doublespinner-rounded INPUT.z-doublespinner-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) #fff repeat-x 0px 0px
}
.z-combobox-rounded .z-combobox-rounded-text-invalid + I.z-combobox-rounded-btn-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-bandbox-rounded .z-bandbox-rounded-text-invalid + I.z-bandbox-rounded-btn-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-datebox-rounded .z-datebox-rounded-text-invalid + I.z-datebox-rounded-btn-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-timebox-rounded .z-timebox-rounded-text-invalid + I.z-timebox-rounded-btn-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-spinner-rounded .z-spinner-rounded-text-invalid + I.z-spinner-rounded-btn-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-doublespinner-rounded .z-doublespinner-rounded-text-invalid + I.z-spinner-rounded-btn-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-combobox-rounded I.z-combobox-rounded-btn-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-bandbox-rounded I.z-bandbox-rounded-btn-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-datebox-rounded I.z-datebox-rounded-btn-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-timebox-rounded I.z-timebox-rounded-btn-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-spinner-rounded I.z-spinner-rounded-btn-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-doublespinner-rounded I.z-doublespinner-rounded-btn-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-combobox-rounded .z-combobox-rounded-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-bandbox-rounded .z-bandbox-rounded-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-datebox-rounded .z-datebox-rounded-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-timebox-rounded .z-timebox-rounded-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-spinner-rounded .z-spinner-rounded-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-doublespinner-rounded .z-doublespinner-rounded-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-combobox .z-combobox-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-bandbox .z-bandbox-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-datebox .z-datebox-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-timebox .z-timebox-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-spinner .z-spinner-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-doublespinner .z-doublespinner-btn {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 17px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobtn.gif'))}) no-repeat 0px 0px; BORDER-TOP-WIDTH: 0px; HEIGHT: 19px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer; border-radius: 0 2px 2px 0; -moz-border-radius: 0 2px 2px 0; -webkit-border-radius: 0 2px 2px 0
}
.z-combobox-rounded .z-combobox-rounded-btn {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 24px; BACKGROUND-POSITION: 0px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; BORDER-LEFT-WIDTH: 0px
}
.z-bandbox-rounded .z-bandbox-rounded-btn {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 24px; BACKGROUND-POSITION: 0px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; BORDER-LEFT-WIDTH: 0px
}
.z-datebox-rounded .z-datebox-rounded-btn {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 24px; BACKGROUND-POSITION: 0px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; BORDER-LEFT-WIDTH: 0px
}
.z-timebox-rounded .z-timebox-rounded-btn {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 24px; BACKGROUND-POSITION: 0px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; BORDER-LEFT-WIDTH: 0px
}
.z-spinner-rounded .z-spinner-rounded-btn {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 24px; BACKGROUND-POSITION: 0px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; BORDER-LEFT-WIDTH: 0px
}
.z-doublespinner-rounded .z-doublespinner-rounded-btn {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 24px; BACKGROUND-POSITION: 0px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; BORDER-LEFT-WIDTH: 0px
}
.z-combobox-rounded .z-combobox-rounded-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobox-rounded.gif'))})
}
.z-bandbox-rounded .z-bandbox-rounded-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/bandbox-rounded.gif'))})
}
.z-datebox-rounded .z-datebox-rounded-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/datebox-rounded.gif'))})
}
.z-timebox-rounded .z-timebox-rounded-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-spinner-rounded .z-spinner-rounded-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-doublespinner-rounded .z-doublespinner-rounded-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-combobox-rounded .z-combobox-rounded-btn-right-edge {
	WIDTH: 5px; BACKGROUND-POSITION: -19px -120px; CURSOR: default
}
.z-bandbox-rounded .z-bandbox-rounded-btn-right-edge {
	WIDTH: 5px; BACKGROUND-POSITION: -19px -120px; CURSOR: default
}
.z-datebox-rounded .z-datebox-rounded-btn-right-edge {
	WIDTH: 5px; BACKGROUND-POSITION: -19px -120px; CURSOR: default
}
.z-timebox-rounded .z-timebox-rounded-btn-right-edge {
	WIDTH: 5px; BACKGROUND-POSITION: -19px -120px; CURSOR: default
}
.z-spinner-rounded .z-spinner-rounded-btn-right-edge {
	WIDTH: 5px; BACKGROUND-POSITION: -19px -120px; CURSOR: default
}
.z-doublespinner-rounded .z-doublespinner-rounded-btn-right-edge {
	WIDTH: 5px; BACKGROUND-POSITION: -19px -120px; CURSOR: default
}
.z-combobox .z-combobox-btn-over {
	BACKGROUND-POSITION: -17px 0px
}
.z-bandbox .z-bandbox-btn-over {
	BACKGROUND-POSITION: -17px 0px
}
.z-datebox .z-datebox-btn-over {
	BACKGROUND-POSITION: -17px 0px
}
.z-timebox .z-timebox-btn-over {
	BACKGROUND-POSITION: -17px 0px
}
.z-spinner .z-spinner-btn-over {
	BACKGROUND-POSITION: -17px 0px
}
.z-doublespinner .z-doublespinner-btn-over {
	BACKGROUND-POSITION: -17px 0px
}
.z-combobox-rounded-inp-over {
	BACKGROUND-POSITION: 0px -24px
}
.z-bandbox-rounded-inp-over {
	BACKGROUND-POSITION: 0px -24px
}
.z-datebox-rounded-inp-over {
	BACKGROUND-POSITION: 0px -24px
}
.z-timebox-rounded-inp-over {
	BACKGROUND-POSITION: 0px -24px
}
.z-spinner-rounded-inp-over {
	BACKGROUND-POSITION: 0px -24px
}
.z-doublespinner-rounded-inp-over {
	BACKGROUND-POSITION: 0px -24px
}
.z-combobox-rounded .z-combobox-rounded-btn-over {
	BACKGROUND-POSITION: 0px -144px
}
.z-bandbox-rounded .z-bandbox-rounded-btn-over {
	BACKGROUND-POSITION: 0px -144px
}
.z-datebox-rounded .z-datebox-rounded-btn-over {
	BACKGROUND-POSITION: 0px -144px
}
.z-timebox-rounded .z-timebox-rounded-btn-over {
	BACKGROUND-POSITION: 0px -144px
}
.z-spinner-rounded .z-spinner-rounded-btn-over {
	BACKGROUND-POSITION: 0px -144px
}
.z-doublespinner-rounded .z-doublespinner-rounded-btn-over {
	BACKGROUND-POSITION: 0px -144px
}
.z-combobox-focus .z-combobox-btn {
	BORDER-BOTTOM: #80b9e9 1px solid; BACKGROUND-POSITION: -51px 0px
}
.z-bandbox-focus .z-bandbox-btn {
	BORDER-BOTTOM: #80b9e9 1px solid; BACKGROUND-POSITION: -51px 0px
}
.z-datebox-focus .z-datebox-btn {
	BORDER-BOTTOM: #80b9e9 1px solid; BACKGROUND-POSITION: -51px 0px
}
.z-timebox-focus .z-timebox-btn {
	BORDER-BOTTOM: #80b9e9 1px solid; BACKGROUND-POSITION: -51px 0px
}
.z-spinner-focus .z-spinner-btn {
	BORDER-BOTTOM: #80b9e9 1px solid; BACKGROUND-POSITION: -51px 0px
}
.z-doublespinner-focus .z-doublespinner-btn {
	BORDER-BOTTOM: #80b9e9 1px solid; BACKGROUND-POSITION: -51px 0px
}
.z-combobox-rounded-focus .z-combobox-rounded-btn {
	BACKGROUND-POSITION: 0px -192px
}
.z-bandbox-rounded-focus .z-bandbox-rounded-btn {
	BACKGROUND-POSITION: 0px -192px
}
.z-datebox-rounded-focus .z-datebox-rounded-btn {
	BACKGROUND-POSITION: 0px -192px
}
.z-timebox-rounded-focus .z-timebox-rounded-btn {
	BACKGROUND-POSITION: 0px -192px
}
.z-spinner-rounded-focus .z-spinner-rounded-btn {
	BACKGROUND-POSITION: 0px -192px
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-btn {
	BACKGROUND-POSITION: 0px -192px
}
.z-combobox-rounded-focus .z-combobox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -120px
}
.z-bandbox-rounded-focus .z-bandbox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -120px
}
.z-datebox-rounded-focus .z-datebox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -120px
}
.z-timebox-rounded-focus .z-timebox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -120px
}
.z-spinner-rounded-focus .z-spinner-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -120px
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -120px
}
.z-combobox-focus .z-combobox-btn-over {
	BACKGROUND-POSITION: -68px 0px
}
.z-bandbox-focus .z-bandbox-btn-over {
	BACKGROUND-POSITION: -68px 0px
}
.z-datebox-focus .z-datebox-btn-over {
	BACKGROUND-POSITION: -68px 0px
}
.z-timebox-focus .z-timebox-btn-over {
	BACKGROUND-POSITION: -68px 0px
}
.z-spinner-focus .z-spinner-btn-over {
	BACKGROUND-POSITION: -68px 0px
}
.z-doublespinner-focus .z-doublespinner-btn-over {
	BACKGROUND-POSITION: -68px 0px
}
.z-combobox-rounded-focus .z-combobox-rounded-btn-over {
	BACKGROUND-POSITION: 0px -216px
}
.z-bandbox-rounded-focus .z-bandbox-rounded-btn-over {
	BACKGROUND-POSITION: 0px -216px
}
.z-datebox-rounded-focus .z-datebox-rounded-btn-over {
	BACKGROUND-POSITION: 0px -216px
}
.z-timebox-rounded-focus .z-timebox-rounded-btn-over {
	BACKGROUND-POSITION: 0px -216px
}
.z-spinner-rounded-focus .z-spinner-rounded-btn-over {
	BACKGROUND-POSITION: 0px -216px
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-btn-over {
	BACKGROUND-POSITION: 0px -216px
}
.z-combobox-focus .z-combobox-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-combobox .z-combobox-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-bandbox-focus .z-bandbox-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-bandbox .z-bandbox-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-datebox-focus .z-datebox-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-datebox .z-datebox-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-timebox-focus .z-timebox-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-timebox .z-timebox-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-spinner-focus .z-spinner-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-spinner .z-spinner-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-doublespinner-focus .z-doublespinner-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-doublespinner .z-doublespinner-btn-clk {
	BACKGROUND-POSITION: -34px 0px
}
.z-combobox-rounded-focus .z-combobox-rounded-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-combobox-rounded .z-combobox-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-bandbox-rounded-focus .z-bandbox-rounded-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-bandbox-rounded .z-bandbox-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-datebox-rounded-focus .z-datebox-rounded-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-datebox-rounded .z-datebox-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-timebox-rounded-focus .z-timebox-rounded-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-timebox-rounded .z-timebox-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-spinner-rounded-focus .z-spinner-rounded-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-spinner-rounded .z-spinner-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-doublespinner-rounded .z-doublespinner-inp-clk {
	BACKGROUND-POSITION: 0px -48px
}
.z-combobox-rounded-focus .z-combobox-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-combobox-rounded .z-combobox-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-bandbox-rounded-focus .z-bandbox-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-bandbox-rounded .z-bandbox-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-datebox-rounded-focus .z-datebox-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-datebox-rounded .z-datebox-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-timebox-rounded-focus .z-timebox-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-timebox-rounded .z-timebox-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-spinner-rounded-focus .z-spinner-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-spinner-rounded .z-spinner-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-doublespinner-rounded .z-doublespinner-rounded-btn-clk {
	BACKGROUND-POSITION: 0px -168px
}
.z-combobox-rounded-pp {
	BORDER-BOTTOM: #86a4be 1px solid; POSITION: absolute; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; DISPLAY: block; BACKGROUND: white; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px
}
.z-bandbox-rounded-pp {
	BORDER-BOTTOM: #86a4be 1px solid; POSITION: absolute; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; DISPLAY: block; BACKGROUND: white; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px
}
.z-datebox-rounded-pp {
	BORDER-BOTTOM: #86a4be 1px solid; POSITION: absolute; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; DISPLAY: block; BACKGROUND: white; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px
}
.z-combobox-pp {
	BORDER-BOTTOM: #86a4be 1px solid; POSITION: absolute; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; DISPLAY: block; BACKGROUND: white; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px
}
.z-bandbox-pp {
	BORDER-BOTTOM: #86a4be 1px solid; POSITION: absolute; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; DISPLAY: block; BACKGROUND: white; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px
}
.z-datebox-pp {
	BORDER-BOTTOM: #86a4be 1px solid; POSITION: absolute; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; DISPLAY: block; BACKGROUND: white; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px
}
.z-combobox-rounded-pp {
	FONT-FAMILY: Tahoma; OVERFLOW: auto
}
.z-combobox-pp {
	FONT-FAMILY: Tahoma; OVERFLOW: auto
}
.z-bandbox-rounded-pp {
	FONT-FAMILY: Tahoma; OVERFLOW: auto
}
.z-bandbox-pp {
	FONT-FAMILY: Tahoma; OVERFLOW: auto
}
.z-combobox-rounded-pp .z-comboitem-text {
	WHITE-SPACE: nowrap; FONT-SIZE: 11px; CURSOR: pointer
}
.z-combobox-rounded-pp .z-comboitem-btn {
	WHITE-SPACE: nowrap; FONT-SIZE: 11px; CURSOR: pointer
}
.z-combobox-pp .z-comboitem-text {
	WHITE-SPACE: nowrap; FONT-SIZE: 11px; CURSOR: pointer
}
.z-combobox-pp .z-comboitem-btn {
	WHITE-SPACE: nowrap; FONT-SIZE: 11px; CURSOR: pointer
}
.z-combobox-rounded-pp .z-comboitem-inner {
	PADDING-LEFT: 6px; COLOR: #888; FONT-SIZE: 11px
}
.z-combobox-rounded-pp .z-comboitem-cnt {
	PADDING-LEFT: 6px; COLOR: #888; FONT-SIZE: 11px
}
.z-combobox-pp .z-comboitem-inner {
	PADDING-LEFT: 6px; COLOR: #888; FONT-SIZE: 11px
}
.z-combobox-pp .z-comboitem-cnt {
	PADDING-LEFT: 6px; COLOR: #888; FONT-SIZE: 11px
}
.z-combobox-rounded-pp .z-comboitem {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-combobox-rounded-pp .z-comboitem A {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-combobox-rounded-pp .z-comboitem A:visited {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-combobox-pp .z-comboitem {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-combobox-pp .z-comboitem A {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-combobox-pp .z-comboitem A:visited {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-combobox-rounded-pp .z-comboitem A:hover {
	TEXT-DECORATION: underline
}
.z-combobox-pp .z-comboitem A:hover {
	TEXT-DECORATION: underline
}
.z-combobox-rounded-pp .z-comboitem-seld {
	BORDER-BOTTOM: #6f97d2 1px solid; BORDER-LEFT: #6f97d2 1px solid; BACKGROUND: #b3c8e8; BORDER-TOP: #6f97d2 1px solid; BORDER-RIGHT: #6f97d2 1px solid
}
.z-combobox-pp .z-comboitem-seld {
	BORDER-BOTTOM: #6f97d2 1px solid; BORDER-LEFT: #6f97d2 1px solid; BACKGROUND: #b3c8e8; BORDER-TOP: #6f97d2 1px solid; BORDER-RIGHT: #6f97d2 1px solid
}
.z-combobox-rounded-pp .z-comboitem-over {
	BACKGROUND: #d3effa
}
.z-combobox-pp .z-comboitem-over {
	BACKGROUND: #d3effa
}
.z-combobox-rounded-pp .z-comboitem-over-seld {
	BACKGROUND: #82d5f8
}
.z-combobox-pp .z-comboitem-over-seld {
	BACKGROUND: #82d5f8
}
.z-bandbox .z-bandbox-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/bandbtn.gif'))})
}
.z-datebox-rounded-over {
	BACKGROUND: #dae7f6
}
.z-datebox-over {
	BACKGROUND: #dae7f6
}
.z-datebox .z-datebox-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/datebtn.gif'))})
}
.z-datebox-rounded-pp {
	BORDER-BOTTOM: #888 1px solid; BORDER-LEFT: #888 1px solid; FONT-FAMILY: Tahoma; FONT-SIZE: 11px; BORDER-TOP: #888 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #888 1px solid
}
.z-datebox-pp {
	BORDER-BOTTOM: #888 1px solid; BORDER-LEFT: #888 1px solid; FONT-FAMILY: Tahoma; FONT-SIZE: 11px; BORDER-TOP: #888 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #888 1px solid
}
.z-datebox-rounded-pp .z-datebox-rounded-calyear {
	BACKGROUND: #d8e8f0
}
.z-datebox-pp .z-datebox-calyear {
	BACKGROUND: #d8e8f0
}
.z-datebox-rounded-time {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 20px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
.z-datebox-time {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 20px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
.z-datebox-rounded-time {
	TEXT-ALIGN: center
}
.z-datebox-time {
	TEXT-ALIGN: center
}
.z-datebox-rounded-time-up {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 0px; PADDING-RIGHT: 0px; HEIGHT: 0px; CURSOR: pointer; PADDING-TOP: 0px
}
.z-datebox-rounded-time-down {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 0px; PADDING-RIGHT: 0px; HEIGHT: 0px; CURSOR: pointer; PADDING-TOP: 0px
}
.z-datebox-time-up {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 0px; PADDING-RIGHT: 0px; HEIGHT: 0px; CURSOR: pointer; PADDING-TOP: 0px
}
.z-datebox-time-down {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 0px; PADDING-RIGHT: 0px; HEIGHT: 0px; CURSOR: pointer; PADDING-TOP: 0px
}
.z-datebox-rounded-time-up {
	BORDER-BOTTOM: #004a7f 6px solid; BORDER-LEFT: white 6px solid; OVERFLOW: hidden; BORDER-TOP: white 6px; BORDER-RIGHT: white 6px solid
}
.z-datebox-time-up {
	BORDER-BOTTOM: #004a7f 6px solid; BORDER-LEFT: white 6px solid; OVERFLOW: hidden; BORDER-TOP: white 6px; BORDER-RIGHT: white 6px solid
}
.z-datebox-rounded-time-down {
	BORDER-BOTTOM: white 5px; BORDER-LEFT: white 5px solid; MARGIN-TOP: 3px; OVERFLOW: hidden; BORDER-TOP: #004a7f 5px solid; BORDER-RIGHT: white 5px solid
}
.z-datebox-time-down {
	BORDER-BOTTOM: white 5px; BORDER-LEFT: white 5px solid; MARGIN-TOP: 3px; OVERFLOW: hidden; BORDER-TOP: #004a7f 5px solid; BORDER-RIGHT: white 5px solid
}
.z-datebox-rounded-time-over.z-datebox-rounded-time-up {
	BORDER-BOTTOM-COLOR: #bfbfbf; BORDER-TOP-COLOR: white; BORDER-RIGHT-COLOR: white; BORDER-LEFT-COLOR: white
}
.z-datebox-time-over.z-datebox-time-up {
	BORDER-BOTTOM-COLOR: #bfbfbf; BORDER-TOP-COLOR: white; BORDER-RIGHT-COLOR: white; BORDER-LEFT-COLOR: white
}
.z-datebox-rounded-time-over.z-datebox-rounded-time-down {
	BORDER-BOTTOM-COLOR: white; BORDER-TOP-COLOR: #bfbfbf; BORDER-RIGHT-COLOR: white; BORDER-LEFT-COLOR: white
}
.z-datebox-time-over.z-datebox-time-down {
	BORDER-BOTTOM-COLOR: white; BORDER-TOP-COLOR: #bfbfbf; BORDER-RIGHT-COLOR: white; BORDER-LEFT-COLOR: white
}
.z-timebox-rounded {
	DISPLAY: inline-block
}
.z-spinner-rounded {
	DISPLAY: inline-block
}
.z-doublespinner-rounded {
	DISPLAY: inline-block
}
.z-timebox {
	DISPLAY: inline-block
}
.z-spinner {
	DISPLAY: inline-block
}
.z-doublespinner {
	DISPLAY: inline-block
}
.z-timebox .z-timebox-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebtn.gif'))})
}
.z-spinner .z-spinner-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebtn.gif'))})
}
.z-doublespinner .z-doublespinner-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebtn.gif'))})
}
.z-combobox-rounded-shadow {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); box-shadow: 1px 1px 3px rgba(0,0,0,0.5); border-radius: 3px; -moz-border-radius: 3px; -webkit-border-radius: 3px
}
.z-bandbox-rounded-shadow {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); box-shadow: 1px 1px 3px rgba(0,0,0,0.5); border-radius: 3px; -moz-border-radius: 3px; -webkit-border-radius: 3px
}
.z-datebox-rounded-shadow {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); box-shadow: 1px 1px 3px rgba(0,0,0,0.5); border-radius: 3px; -moz-border-radius: 3px; -webkit-border-radius: 3px
}
.z-combobox-shadow {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); box-shadow: 1px 1px 3px rgba(0,0,0,0.5); border-radius: 3px; -moz-border-radius: 3px; -webkit-border-radius: 3px
}
.z-bandbox-shadow {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); box-shadow: 1px 1px 3px rgba(0,0,0,0.5); border-radius: 3px; -moz-border-radius: 3px; -webkit-border-radius: 3px
}
.z-datebox-shadow {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); box-shadow: 1px 1px 3px rgba(0,0,0,0.5); border-radius: 3px; -moz-border-radius: 3px; -webkit-border-radius: 3px
}
.z-spinner-rounded-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-doublespinner-rounded-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-timebox-rounded-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-datebox-rounded-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-bandbox-rounded-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-combobox-rounded-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-spinner-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-doublespinner-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-timebox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-datebox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-bandbox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-comboitem-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-combobox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-spinner-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-spinner-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-doublespinner-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-doublespinner-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-timebox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-timebox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-datebox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-datebox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-bandbox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-bandbox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-combobox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-combobox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-spinner-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-spinner-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-doublespinner-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-doublespinner-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-timebox-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-timebox-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-datebox-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-datebox-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-bandbox-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-bandbox-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-comboitem-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-comboitem-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-combobox-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-combobox-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-timebox-rounded-disd {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-timebox-disd {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-comboitem-text-disd {
	BACKGROUND: #eceae4
}
.z-spinner-text-disd {
	BACKGROUND: #eceae4
}
.z-doublespinner-text-disd {
	BACKGROUND: #eceae4
}
.z-timebox-text-disd {
	BACKGROUND: #eceae4
}
.z-datebox-text-disd {
	BACKGROUND: #eceae4
}
.z-bandbox-text-disd {
	BACKGROUND: #eceae4
}
.z-combobox-text-disd {
	BACKGROUND: #eceae4
}
.z-spinner-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-doublespinner-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-timebox-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-datebox-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-bandbox-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-combobox-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-spinner-focus .z-spinner-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-doublespinner-focus .z-doublespinner-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-timebox-focus .z-timebox-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-datebox-focus .z-datebox-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-bandbox-focus .z-bandbox-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-combobox-focus .z-combobox-readonly {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-bg.gif'))}) repeat-x 0px 0px
}
.z-combobox-rounded-readonly {
	BACKGROUND-POSITION: 0px -72px
}
.z-bandbox-rounded-readonly {
	BACKGROUND-POSITION: 0px -72px
}
.z-datebox-rounded-readonly {
	BACKGROUND-POSITION: 0px -72px
}
.z-timebox-rounded-readonly {
	BACKGROUND-POSITION: 0px -72px
}
.z-spinner-rounded-readonly {
	BACKGROUND-POSITION: 0px -72px
}
.z-doublespinner-rounded-readonly {
	BACKGROUND-POSITION: 0px -72px
}
.z-combobox-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobox-rounded.gif'))})
}
.z-bandbox-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/bandbox-rounded.gif'))})
}
.z-datebox-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/datebox-rounded.gif'))})
}
.z-timebox-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-spinner-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-doublespinner-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-combobox-rounded .z-combobox-rounded-btn-readonly.z-combobox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-combobox-rounded I.z-combobox-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -192px
}
.z-bandbox-rounded .z-bandbox-rounded-btn-readonly.z-bandbox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-bandbox-rounded I.z-bandbox-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -192px
}
.z-datebox-rounded .z-datebox-rounded-btn-readonly.z-datebox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-datebox-rounded I.z-datebox-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -192px
}
.z-timebox-rounded .z-timebox-rounded-btn-readonly.z-timebox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-timebox-rounded I.z-timebox-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -192px
}
.z-spinner-rounded .z-spinner-rounded-btn-readonly.z-spinner-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-doublespinner-rounded .z-doublespinner-rounded-btn-readonly.z-doublespinner-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-spinner-rounded I.z-spinner-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -192px
}
.z-doublespinner-rounded I.z-doublespinner-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -192px
}
.z-combobox-rounded .z-combobox-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -192px
}
.z-bandbox-rounded .z-bandbox-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -192px
}
.z-datebox-rounded .z-datebox-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -192px
}
.z-timebox-rounded .z-timebox-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -192px
}
.z-spinner-rounded .z-spinner-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -192px
}
.z-doublespinner-rounded .z-doublespinner-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -192px
}
.z-spinner-focus .z-spinner-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-focus-bg.gif'))})
}
.z-doublespinner-focus .z-doublespinner-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-focus-bg.gif'))})
}
.z-timebox-focus .z-timebox-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-focus-bg.gif'))})
}
.z-datebox-focus .z-datebox-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-focus-bg.gif'))})
}
.z-bandbox-focus .z-bandbox-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-focus-bg.gif'))})
}
.z-combobox-focus .z-combobox-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/readonly-focus-bg.gif'))})
}
.z-combobox-rounded-focus .z-combobox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-bandbox-rounded-focus .z-bandbox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-datebox-rounded-focus .z-datebox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-timebox-rounded-focus .z-timebox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-spinner-rounded-focus .z-spinner-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-combobox-rounded-focus .z-combobox-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/combobox-rounded.gif'))})
}
.z-bandbox-rounded-focus .z-bandbox-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/bandbox-rounded.gif'))})
}
.z-datebox-rounded-focus .z-datebox-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/datebox-rounded.gif'))})
}
.z-timebox-rounded-focus .z-timebox-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-spinner-rounded-focus .z-spinner-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-readonly {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))})
}
.z-combobox-rounded-focus .z-combobox-rounded-btn-readonly.z-combobox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-combobox-rounded-focus I.z-combobox-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-bandbox-rounded-focus .z-bandbox-rounded-btn-readonly.z-bandbox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-bandbox-rounded-focus I.z-bandbox-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-datebox-rounded-focus .z-datebox-rounded-btn-readonly.z-datebox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-datebox-rounded-focus I.z-datebox-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-timebox-rounded-focus .z-timebox-rounded-btn-readonly.z-timebox-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-timebox-rounded-focus I.z-timebox-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-spinner-rounded-focus .z-spinner-rounded-btn-readonly.z-spinner-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-btn-readonly.z-doublespinner-rounded-btn-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-spinner-rounded-focus I.z-spinner-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-doublespinner-rounded-focus I.z-doublespinner-rounded-btn-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-combobox-rounded-focus .z-combobox-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -216px
}
.z-bandbox-rounded-focus .z-bandbox-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -216px
}
.z-datebox-rounded-focus .z-datebox-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -216px
}
.z-timebox-rounded-focus .z-timebox-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -216px
}
.z-spinner-rounded-focus .z-spinner-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -216px
}
.z-doublespinner-rounded-focus .z-doublespinner-rounded-btn-readonly {
	BACKGROUND-POSITION: 0px -216px
}
.z-combobox-inplace {
	PADDING-BOTTOM: 1px; BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 1px
}
.z-bandbox-inplace {
	PADDING-BOTTOM: 1px; BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 1px
}
.z-datebox-inplace {
	PADDING-BOTTOM: 1px; BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 1px
}
.z-timebox-inplace {
	PADDING-BOTTOM: 1px; BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 1px
}
.z-spinner-inplace {
	PADDING-BOTTOM: 1px; BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 1px
}
.z-doublespinner-inplace {
	PADDING-BOTTOM: 1px; BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 1px
}
.z-combobox-rounded-inplace .z-combobox-rounded-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-bandbox-rounded-inplace .z-bandbox-rounded-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-datebox-rounded-inplace .z-datebox-rounded-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-timebox-rounded-inplace .z-timebox-rounded-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-spinner-rounded-inplace .z-spinner-rounded-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-doublespinner-rounded-inplace .z-doublespinner-rounded-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-combobox-inplace .z-combobox-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-bandbox-inplace .z-bandbox-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-datebox-inplace .z-datebox-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-timebox-inplace .z-timebox-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-spinner-inplace .z-spinner-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-doublespinner-inplace .z-doublespinner-inp {
	PADDING-BOTTOM: 2px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 2px
}
.z-combobox-rounded-inplace .z-combobox-rounded-inp {
	PADDING-BOTTOM: 5px; BACKGROUND: none transparent scroll repeat 0% 0%; PADDING-TOP: 5px
}
.z-bandbox-rounded-inplace .z-bandbox-rounded-inp {
	PADDING-BOTTOM: 5px; BACKGROUND: none transparent scroll repeat 0% 0%; PADDING-TOP: 5px
}
.z-datebox-rounded-inplace .z-datebox-rounded-inp {
	PADDING-BOTTOM: 5px; BACKGROUND: none transparent scroll repeat 0% 0%; PADDING-TOP: 5px
}
.z-timebox-rounded-inplace .z-timebox-rounded-inp {
	PADDING-BOTTOM: 5px; BACKGROUND: none transparent scroll repeat 0% 0%; PADDING-TOP: 5px
}
.z-spinner-rounded-inplace .z-spinner-rounded-inp {
	PADDING-BOTTOM: 5px; BACKGROUND: none transparent scroll repeat 0% 0%; PADDING-TOP: 5px
}
.z-doublespinner-rounded-inplace .z-doublespinner-rounded-inp {
	PADDING-BOTTOM: 5px; BACKGROUND: none transparent scroll repeat 0% 0%; PADDING-TOP: 5px
}
.z-combobox-inplace .z-combobox-inp {
	BORDER-RIGHT-WIDTH: 0px !important
}
.z-bandbox-inplace .z-bandbox-inp {
	BORDER-RIGHT-WIDTH: 0px !important
}
.z-datebox-inplace .z-datebox-inp {
	BORDER-RIGHT-WIDTH: 0px !important
}
.z-timebox-inplace .z-timebox-inp {
	BORDER-RIGHT-WIDTH: 0px !important
}
.z-spinner-inplace .z-spinner-inp {
	BORDER-RIGHT-WIDTH: 0px !important
}
.z-doublespinner-inplace .z-doublespinner-inp {
	BORDER-RIGHT-WIDTH: 0px !important
}
.z-combobox-inplace .z-combobox-btn {
	DISPLAY: none
}
.z-bandbox-inplace .z-bandbox-btn {
	DISPLAY: none
}
.z-datebox-inplace .z-datebox-btn {
	DISPLAY: none
}
.z-timebox-inplace .z-timebox-btn {
	DISPLAY: none
}
.z-spinner-inplace .z-spinner-btn {
	DISPLAY: none
}
.z-doublespinner-inplace .z-doublespinner-btn {
	DISPLAY: none
}
.z-combobox-rounded-inplace .z-combobox-rounded-btn {
	BACKGROUND: none transparent scroll repeat 0% 0%; VISIBILITY: hidden
}
.z-bandbox-rounded-inplace .z-bandbox-rounded-btn {
	BACKGROUND: none transparent scroll repeat 0% 0%; VISIBILITY: hidden
}
.z-datebox-rounded-inplace .z-datebox-rounded-btn {
	BACKGROUND: none transparent scroll repeat 0% 0%; VISIBILITY: hidden
}
.z-timebox-rounded-inplace .z-timebox-rounded-btn {
	BACKGROUND: none transparent scroll repeat 0% 0%; VISIBILITY: hidden
}
.z-spinner-rounded-inplace .z-spinner-rounded-btn {
	BACKGROUND: none transparent scroll repeat 0% 0%; VISIBILITY: hidden
}
.z-doublespinner-rounded-inplace .z-doublespinner-rounded-btn {
	BACKGROUND: none transparent scroll repeat 0% 0%; VISIBILITY: hidden
}
.z-combobox-rounded-pp .z-comboitem-inner {
	PADDING-LEFT: 5px
}
.z-combobox-pp .z-comboitem-inner {
	PADDING-LEFT: 5px
}

@media Print
{
.z-borderlayout {

}

}

@media Screen
{
.z-borderlayout {
	POSITION: relative
}
    }
.z-borderlayout {
	BORDER-RIGHT-WIDTH: 0px; BACKGROUND-COLOR: #cde6f5; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 100%; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px
}
.z-north {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: white; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-south {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: white; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-west {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: white; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-center {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: white; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-east {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: white; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-north {
	WIDTH: 100%
}
.z-south {
	WIDTH: 100%
}
.z-center {
	WIDTH: 100%
}
.z-west {
	HEIGHT: 100%
}
.z-east {
	HEIGHT: 100%
}
.z-west-noborder {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-center-noborder {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-east-noborder {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-north-noborder {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-south-noborder {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-east-splt {
	POSITION: absolute; LINE-HEIGHT: 0; WIDTH: 8px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-h.png'))}) #c4dcfb left 50%; HEIGHT: 8px; FONT-SIZE: 0px; CURSOR: e-resize
}
.z-west-splt {
	POSITION: absolute; LINE-HEIGHT: 0; WIDTH: 8px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-h.png'))}) #c4dcfb left 50%; HEIGHT: 8px; FONT-SIZE: 0px; CURSOR: e-resize
}
.z-north-splt {
	POSITION: absolute; LINE-HEIGHT: 0; WIDTH: 8px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-h.png'))}) #c4dcfb left 50%; HEIGHT: 8px; FONT-SIZE: 0px; CURSOR: e-resize
}
.z-south-splt {
	POSITION: absolute; LINE-HEIGHT: 0; WIDTH: 8px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-h.png'))}) #c4dcfb left 50%; HEIGHT: 8px; FONT-SIZE: 0px; CURSOR: e-resize
}
.z-north-splt {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-v.png'))}) #c4dcfb 50% top; CURSOR: s-resize
}
.z-south-splt {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-v.png'))}) #c4dcfb 50% top; CURSOR: s-resize
}
.z-west {
	Z-INDEX: 12
}
.z-west-colpsd {
	Z-INDEX: 12
}
.z-center {
	Z-INDEX: 8
}
.z-east {
	Z-INDEX: 10
}
.z-east-colpsd {
	Z-INDEX: 10
}
.z-north {
	Z-INDEX: 16
}
.z-north-colpsd {
	Z-INDEX: 16
}
.z-south {
	Z-INDEX: 14
}
.z-south-colpsd {
	Z-INDEX: 14
}
.z-west-splt {
	Z-INDEX: 11
}
.z-east-splt {
	Z-INDEX: 9
}
.z-north-splt {
	Z-INDEX: 15
}
.z-south-splt {
	Z-INDEX: 13
}
.z-borderlayout-icon {
	WIDTH: 16px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/layout/borderlayout-btn.png'))}) no-repeat 0px 0px; FLOAT: right; HEIGHT: 16px; MARGIN-LEFT: 2px; OVERFLOW: hidden; CURSOR: pointer
}
.z-west-header {
	BORDER-BOTTOM: #9ecad8 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 15px; PADDING-LEFT: 5px; PADDING-RIGHT: 3px; ZOOM: 1; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/layout/borderlayout-hm.png'))}) repeat-x 0px 0px; COLOR: #0f3b82; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: bold; PADDING-TOP: 5px
}
.z-center-header {
	BORDER-BOTTOM: #9ecad8 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 15px; PADDING-LEFT: 5px; PADDING-RIGHT: 3px; ZOOM: 1; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/layout/borderlayout-hm.png'))}) repeat-x 0px 0px; COLOR: #0f3b82; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: bold; PADDING-TOP: 5px
}
.z-east-header {
	BORDER-BOTTOM: #9ecad8 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 15px; PADDING-LEFT: 5px; PADDING-RIGHT: 3px; ZOOM: 1; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/layout/borderlayout-hm.png'))}) repeat-x 0px 0px; COLOR: #0f3b82; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: bold; PADDING-TOP: 5px
}
.z-north-header {
	BORDER-BOTTOM: #9ecad8 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 15px; PADDING-LEFT: 5px; PADDING-RIGHT: 3px; ZOOM: 1; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/layout/borderlayout-hm.png'))}) repeat-x 0px 0px; COLOR: #0f3b82; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: bold; PADDING-TOP: 5px
}
.z-south-header {
	BORDER-BOTTOM: #9ecad8 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 15px; PADDING-LEFT: 5px; PADDING-RIGHT: 3px; ZOOM: 1; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/layout/borderlayout-hm.png'))}) repeat-x 0px 0px; COLOR: #0f3b82; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: bold; PADDING-TOP: 5px
}
.z-north-exp {
	MARGIN: 3px; FLOAT: right
}
.z-south-exp {
	MARGIN: 3px; FLOAT: right
}
.z-east-exp {
	MARGIN: 3px auto; FLOAT: none
}
.z-west-exp {
	MARGIN: 3px auto; FLOAT: none
}
.z-north-colps {
	BACKGROUND-POSITION: 0px 0px
}
.z-south-exp {
	BACKGROUND-POSITION: 0px 0px
}
.z-north-colps-over {
	BACKGROUND-POSITION: -16px 0px
}
.z-south-exp-over {
	BACKGROUND-POSITION: -16px 0px
}
.z-east-colps {
	BACKGROUND-POSITION: 0px -16px
}
.z-west-exp {
	BACKGROUND-POSITION: 0px -16px
}
.z-east-colps-over {
	BACKGROUND-POSITION: -16px -16px
}
.z-west-exp-over {
	BACKGROUND-POSITION: -16px -16px
}
.z-south-colps {
	BACKGROUND-POSITION: 0px -32px
}
.z-north-exp {
	BACKGROUND-POSITION: 0px -32px
}
.z-south-colps-over {
	BACKGROUND-POSITION: -16px -32px
}
.z-north-exp-over {
	BACKGROUND-POSITION: -16px -32px
}
.z-west-colps {
	BACKGROUND-POSITION: 0px -48px
}
.z-east-exp {
	BACKGROUND-POSITION: 0px -48px
}
.z-west-colps-over {
	BACKGROUND-POSITION: -16px -48px
}
.z-east-exp-over {
	BACKGROUND-POSITION: -16px -48px
}
.z-east-colpsd {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: #e1f0f2; WIDTH: 22px; HEIGHT: 22px; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-west-colpsd {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: #e1f0f2; WIDTH: 22px; HEIGHT: 22px; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-south-colpsd {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: #e1f0f2; WIDTH: 22px; HEIGHT: 22px; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-north-colpsd {
	BORDER-BOTTOM: #9ecad8 1px solid; POSITION: absolute; BORDER-LEFT: #9ecad8 1px solid; BACKGROUND-COLOR: #e1f0f2; WIDTH: 22px; HEIGHT: 22px; OVERFLOW: hidden; BORDER-TOP: #9ecad8 1px solid; BORDER-RIGHT: #9ecad8 1px solid
}
.z-east-colpsd-over {
	BACKGROUND-COLOR: #eefcff; CURSOR: pointer
}
.z-west-colpsd-over {
	BACKGROUND-COLOR: #eefcff; CURSOR: pointer
}
.z-south-colpsd-over {
	BACKGROUND-COLOR: #eefcff; CURSOR: pointer
}
.z-north-colpsd-over {
	BACKGROUND-COLOR: #eefcff; CURSOR: pointer
}
.z-east-splt-btn {
	FILTER: alpha(opacity=50); LINE-HEIGHT: 1px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; FONT-SIZE: 1px; VERTICAL-ALIGN: top; CURSOR: pointer; opacity: .5
}
.z-west-splt-btn {
	FILTER: alpha(opacity=50); LINE-HEIGHT: 1px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; FONT-SIZE: 1px; VERTICAL-ALIGN: top; CURSOR: pointer; opacity: .5
}
.z-north-splt-btn {
	FILTER: alpha(opacity=50); LINE-HEIGHT: 1px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; FONT-SIZE: 1px; VERTICAL-ALIGN: top; CURSOR: pointer; opacity: .5
}
.z-south-splt-btn {
	FILTER: alpha(opacity=50); LINE-HEIGHT: 1px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; FONT-SIZE: 1px; VERTICAL-ALIGN: top; CURSOR: pointer; opacity: .5
}
.z-east-splt-btn-over {
	FILTER: alpha(opacity=100); opacity: 1
}
.z-west-splt-btn-over {
	FILTER: alpha(opacity=100); opacity: 1
}
.z-north-splt-btn-over {
	FILTER: alpha(opacity=100); opacity: 1
}
.z-south-splt-btn-over {
	FILTER: alpha(opacity=100); opacity: 1
}
.z-west-splt-btn {
	MIN-HEIGHT: 50px; WIDTH: 6px; HEIGHT: 50px
}
.z-east-splt-btn {
	MIN-HEIGHT: 50px; WIDTH: 6px; HEIGHT: 50px
}
.z-west-splt-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-l.png'))})
}
.z-east-splt-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-r.png'))})
}
.z-north-splt-btn {
	MIN-HEIGHT: 5px; WIDTH: 50px; HEIGHT: 6px
}
.z-south-splt-btn {
	MIN-HEIGHT: 5px; WIDTH: 50px; HEIGHT: 6px
}
.z-north-splt-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-t.png'))})
}
.z-south-splt-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-b.png'))})
}
.z-button {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; COLOR: black; FONT-SIZE: 11px; CURSOR: pointer
}
.z-button TR TD {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; COLOR: black; FONT-SIZE: 11px; CURSOR: pointer
}
BUTTON.z-button {
	PADDING-BOTTOM: 0px !important; LINE-HEIGHT: 0 !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; WIDTH: 3px !important; PADDING-RIGHT: 0px !important; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 1px !important; FONT-SIZE: 0px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
.z-button .z-button-cr * {
	LINE-HEIGHT: 0 !important; WIDTH: 1px !important; DISPLAY: block; FONT-SIZE: 0px !important; OVERFLOW: hidden
}
SPAN.z-button {
	MARGIN: 1px 1px 0px 0px; DISPLAY: inline-block; VERTICAL-ALIGN: bottom
}
.z-button .z-button-cr * {
	WIDTH: 3px !important
}
.z-button-disd {
	FILTER: alpha(opacity=60); COLOR: gray; CURSOR: default; opacity: .6; -moz-opacity: .6
}
.z-button .z-button-tl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-corner.gif'))})
}
.z-button .z-button-tr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-corner.gif'))})
}
.z-button .z-button-bl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-corner.gif'))})
}
.z-button .z-button-br {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-corner.gif'))})
}
.z-button .z-button-tm {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-x.gif'))})
}
.z-button .z-button-bm {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-x.gif'))})
}
.z-button .z-button-cl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-y.gif'))})
}
.z-button .z-button-cr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-y.gif'))})
}
.z-button .z-button-cm {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/btn-ctr.gif'))})
}
.z-button .z-button-tl {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 3px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: -3px 0px; PADDING-TOP: 0px
}
.z-button .z-button-tr {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 3px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: -3px 0px; PADDING-TOP: 0px
}
.z-button .z-button-cl {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 3px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: -3px 0px; PADDING-TOP: 0px
}
.z-button .z-button-cr {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 3px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: -3px 0px; PADDING-TOP: 0px
}
.z-button .z-button-tl {
	HEIGHT: 3px
}
.z-button .z-button-tr {
	HEIGHT: 3px
}
.z-button .z-button-tl {
	BACKGROUND-POSITION: 0px 0px
}
.z-button .z-button-cl {
	TEXT-ALIGN: right; BACKGROUND-POSITION: 0px 0px
}
.z-button .z-button-tm {
	BACKGROUND-REPEAT: repeat-x; BACKGROUND-POSITION: 0px 0px
}
.z-button .z-button-tr {
	BACKGROUND-POSITION: -3px 0px
}
.z-button .z-button-cm {
	TEXT-ALIGN: center; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; BACKGROUND-REPEAT: repeat-x; WHITE-SPACE: nowrap; BACKGROUND-POSITION: 0px 0px; VERTICAL-ALIGN: middle; OVERFLOW: hidden; PADDING-TOP: 0px
}
.z-button .z-button-bl {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 3px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 0px -3px; HEIGHT: 3px; PADDING-TOP: 0px
}
.z-button .z-button-br {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 3px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 0px -3px; HEIGHT: 3px; PADDING-TOP: 0px
}
.z-button .z-button-bm {
	BACKGROUND-REPEAT: repeat-x; BACKGROUND-POSITION: 0px -3px; HEIGHT: 3px
}
.z-button .z-button-br {
	BACKGROUND-POSITION: -3px -3px
}
.z-button-over .z-button-tl {
	BACKGROUND-POSITION: -6px 0px
}
.z-button-over .z-button-cl {
	BACKGROUND-POSITION: -6px 0px
}
.z-button-over .z-button-tm {
	BACKGROUND-POSITION: 0px -6px
}
.z-button-over .z-button-tr {
	BACKGROUND-POSITION: -9px 0px
}
.z-button-over .z-button-cr {
	BACKGROUND-POSITION: -9px 0px
}
.z-button-over .z-button-cm {
	BACKGROUND-POSITION: 0px -500px
}
.z-button-over .z-button-bl {
	BACKGROUND-POSITION: -6px -3px
}
.z-button-over .z-button-bm {
	BACKGROUND-POSITION: 0px -9px
}
.z-button-over .z-button-br {
	BACKGROUND-POSITION: -9px -3px
}
.z-button-focus .z-button-tl {
	BACKGROUND-POSITION: -12px 0px
}
.z-button-focus .z-button-cl {
	BACKGROUND-POSITION: -12px 0px
}
.z-button-focus .z-button-tm {
	BACKGROUND-POSITION: 0px -12px
}
.z-button-focus .z-button-tr {
	BACKGROUND-POSITION: -15px 0px
}
.z-button-focus .z-button-cr {
	BACKGROUND-POSITION: -15px 0px
}
.z-button-focus .z-button-bl {
	BACKGROUND-POSITION: -12px -3px
}
.z-button-focus .z-button-bm {
	BACKGROUND-POSITION: 0px -15px
}
.z-button-focus .z-button-br {
	BACKGROUND-POSITION: -15px -3px
}
.z-button-clk .z-button-tl {
	BACKGROUND-POSITION: -6px 0px
}
.z-button-clk .z-button-cl {
	BACKGROUND-POSITION: -6px 0px
}
.z-button-clk .z-button-tr {
	BACKGROUND-POSITION: -9px 0px
}
.z-button-clk .z-button-cr {
	BACKGROUND-POSITION: -9px 0px
}
.z-button-clk .z-button-tm {
	BACKGROUND-POSITION: 0px -18px
}
.z-button-clk .z-button-bm {
	BACKGROUND-POSITION: 0px -21px
}
.z-button-clk .z-button-cm {
	BACKGROUND-POSITION: 0px -1000px
}
.z-button-clk .z-button-br {
	BACKGROUND-POSITION: -9px -3px
}
.z-button-clk .z-button-bl {
	BACKGROUND-POSITION: -6px -3px
}
.z-button-os {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
TD.z-hbox-sep {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
TR.z-vbox-sep {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
TD.z-hbox-sep {
	WIDTH: 0.3em
}
TR.z-vbox-sep {
	HEIGHT: 0.3em
}
.z-splitter-ver-btn-t {
	FONT-SIZE: 0px
}
.z-splitter-ver-btn-b {
	FONT-SIZE: 0px
}
.z-splitter-hor-btn-l {
	FONT-SIZE: 0px
}
.z-splitter-hor-btn-r {
	FONT-SIZE: 0px
}
.z-splitter-hor {
	FONT-SIZE: 0px
}
.z-splitter-ver {
	FONT-SIZE: 0px
}
.z-splitter-os-ver-btn-t {
	FONT-SIZE: 0px
}
.z-splitter-os-ver-btn-b {
	FONT-SIZE: 0px
}
.z-splitter-os-hor-btn-l {
	FONT-SIZE: 0px
}
.z-splitter-os-hor-btn-r {
	FONT-SIZE: 0px
}
.z-splitter-os-hor {
	FONT-SIZE: 0px
}
.z-splitter-os-ver {
	FONT-SIZE: 0px
}
.z-splitter-os-hor-ns {
	FONT-SIZE: 0px
}
.z-splitter-os-ver-ns {
	FONT-SIZE: 0px
}
.z-splitter-hor SPAN {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-ver SPAN {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-ver-btn-t {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-ver-btn-b {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-hor-btn-l {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-hor-btn-r {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-os-ver-btn-t {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-os-ver-btn-b {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-os-hor-btn-l {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-os-hor-btn-r {
	DISPLAY: inline-block; VERTICAL-ALIGN: top; CURSOR: pointer
}
.z-splitter-hor-outer {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-h-ns.png'))}) repeat-y right top
}
.z-splitter-os-hor-outer {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-h-ns.png'))}) repeat-y right top
}
.z-splitter-os-hor-outer {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-h-os.gif'))})
}
.z-splitter-ver-outer .z-splitter-ver-outer-td {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-v-ns.png'))}) repeat-x left bottom
}
.z-splitter-os-ver-outer .z-splitter-os-ver-outer-td {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-v-ns.png'))}) repeat-x left bottom
}
.z-splitter-os-ver-outer .z-splitter-os-ver-outer-td {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-v-os.gif'))})
}
.z-splitter-hor {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-h.png'))}) no-repeat left center
}
.z-splitter-hor {
	CURSOR: e-resize
}
.z-splitter-os-hor {
	CURSOR: e-resize
}
.z-splitter-hor {
	WIDTH: 8px; MAX-WIDTH: 8px
}
.z-splitter-hor-outer {
	WIDTH: 8px; MAX-WIDTH: 8px
}
.z-splitter-os-hor {
	WIDTH: 8px; MAX-WIDTH: 8px
}
.z-splitter-os-hor-ns {
	WIDTH: 8px; MAX-WIDTH: 8px
}
.z-splitter-os-hor-outer {
	WIDTH: 8px; MAX-WIDTH: 8px
}
.z-splitter-ver {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/splt-v.png'))}) no-repeat center top
}
.z-splitter-ver {
	CURSOR: s-resize
}
.z-splitter-os-ver {
	CURSOR: s-resize
}
.z-splitter-ver {
	HEIGHT: 8px; MAX-HEIGHT: 8px
}
.z-splitter-os-ver {
	HEIGHT: 8px; MAX-HEIGHT: 8px
}
.z-splitter-os-ver-ns {
	HEIGHT: 8px; MAX-HEIGHT: 8px
}
.z-splitter-ver-outer .z-splitter-ver-outer-td {
	HEIGHT: 8px; MAX-HEIGHT: 8px
}
.z-splitter-os-ver-outer .z-splitter-os-ver-outer-td {
	HEIGHT: 8px; MAX-HEIGHT: 8px
}
.z-splitter-hor-ns {
	BACKGROUND-IMAGE: none
}
.z-splitter-ver-ns {
	BACKGROUND-IMAGE: none
}
.z-splitter-hor-ns {
	CURSOR: default
}
.z-splitter-ver-ns {
	CURSOR: default
}
.z-splitter-os-hor-ns {
	CURSOR: default
}
.z-splitter-os-ver-ns {
	CURSOR: default
}
.z-splitter-ver-btn-t:hover {
	opacity: 1
}
.z-splitter-ver-btn-b:hover {
	opacity: 1
}
.z-splitter-hor-btn-l:hover {
	opacity: 1
}
.z-splitter-hor-btn-r:hover {
	opacity: 1
}
.z-splitter-os-ver-btn-t:hover {
	opacity: 1
}
.z-splitter-os-ver-btn-b:hover {
	opacity: 1
}
.z-splitter-os-hor-btn-l:hover {
	opacity: 1
}
.z-splitter-os-hor-btn-r:hover {
	opacity: 1
}
.z-splitter-ver-btn-t {
	FILTER: alpha(opacity=50); BACKGROUND-REPEAT: no-repeat; opacity: .5
}
.z-splitter-ver-btn-b {
	FILTER: alpha(opacity=50); BACKGROUND-REPEAT: no-repeat; opacity: .5
}
.z-splitter-hor-btn-l {
	FILTER: alpha(opacity=50); BACKGROUND-REPEAT: no-repeat; opacity: .5
}
.z-splitter-hor-btn-r {
	FILTER: alpha(opacity=50); BACKGROUND-REPEAT: no-repeat; opacity: .5
}
.z-splitter-os-ver-btn-t {
	FILTER: alpha(opacity=50); BACKGROUND-REPEAT: no-repeat; opacity: .5
}
.z-splitter-os-ver-btn-b {
	FILTER: alpha(opacity=50); BACKGROUND-REPEAT: no-repeat; opacity: .5
}
.z-splitter-os-hor-btn-l {
	FILTER: alpha(opacity=50); BACKGROUND-REPEAT: no-repeat; opacity: .5
}
.z-splitter-os-hor-btn-r {
	FILTER: alpha(opacity=50); BACKGROUND-REPEAT: no-repeat; opacity: .5
}
.z-splitter-ver-btn-visi {
	FILTER: alpha(opacity=100) !important
}
.z-splitter-hor-btn-visi {
	FILTER: alpha(opacity=100) !important
}
.z-splitter-hor-btn-l {
	MIN-HEIGHT: 50px; HEIGHT: 50px
}
.z-splitter-hor-btn-r {
	MIN-HEIGHT: 50px; HEIGHT: 50px
}
.z-splitter-os-hor-btn-l {
	MIN-HEIGHT: 50px; HEIGHT: 50px
}
.z-splitter-os-hor-btn-r {
	MIN-HEIGHT: 50px; HEIGHT: 50px
}
.z-splitter-hor-btn-l {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-l.png'))}); WIDTH: 6px
}
.z-splitter-hor-btn-r {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-l.png'))}); WIDTH: 6px
}
.z-splitter-hor-btn-r {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-r.png'))})
}
.z-splitter-ver-btn-t {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-t.png'))}); MIN-HEIGHT: 6px; WIDTH: 50px; HEIGHT: 6px
}
.z-splitter-ver-btn-b {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-t.png'))}); MIN-HEIGHT: 6px; WIDTH: 50px; HEIGHT: 6px
}
.z-splitter-ver-btn-b {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-b.png'))})
}
.z-splitter-os-hor-btn-l {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-l-os.gif'))}); WIDTH: 8px
}
.z-splitter-os-hor-btn-r {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-l-os.gif'))}); WIDTH: 8px
}
.z-splitter-os-hor-btn-r {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-r-os.gif'))})
}
.z-splitter-os-ver-btn-t {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-t-os.gif'))}); MIN-HEIGHT: 8px; WIDTH: 50px; HEIGHT: 8px
}
.z-splitter-os-ver-btn-b {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-t-os.gif'))}); MIN-HEIGHT: 8px; WIDTH: 50px; HEIGHT: 8px
}
.z-splitter-os-ver-btn-b {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/splt/colps-b-os.gif'))})
}
.z-calendar {
	BORDER-BOTTOM: #c5c5c5 1px solid; BORDER-LEFT: #c5c5c5 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; WIDTH: 215px; PADDING-RIGHT: 2px; FONT-FAMILY: Tahoma; BACKGROUND: white; FONT-SIZE: 11px; BORDER-TOP: #c5c5c5 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #c5c5c5 1px solid; PADDING-TOP: 2px
}
.z-calendar {
	border-radius: 3px; -moz-border-radius: 3px; -webkit-border-radius: 3px
}
.z-calendar-title-over {
	border-radius: 3px; -moz-border-radius: 3px; -webkit-border-radius: 3px
}
.z-datebox-rounded-pp .z-calendar {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-datebox-pp .z-calendar {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-calendar-title-over {
	COLOR: #5fa4ff
}
.z-calendar-tdl {
	POSITION: relative; PADDING-BOTTOM: 10px; WIDTH: 16px; CURSOR: pointer
}
.z-calendar-tdr {
	POSITION: relative; PADDING-BOTTOM: 10px; WIDTH: 16px; CURSOR: pointer
}
.z-calendar-left {
	POSITION: relative
}
.z-calendar-right {
	POSITION: relative
}
.z-calendar-left-icon {
	BORDER-BOTTOM: white 6px solid; POSITION: absolute; LINE-HEIGHT: 0; WIDTH: 0px; HEIGHT: 0px; FONT-SIZE: 0px; BORDER-TOP: white 6px solid; CURSOR: pointer
}
.z-calendar-right-icon {
	BORDER-BOTTOM: white 6px solid; POSITION: absolute; LINE-HEIGHT: 0; WIDTH: 0px; HEIGHT: 0px; FONT-SIZE: 0px; BORDER-TOP: white 6px solid; CURSOR: pointer
}
.z-calendar-left-icon {
	BORDER-LEFT-WIDTH: 0px; RIGHT: 0px; BORDER-RIGHT: #656565 6px solid
}
.z-calendar-right-icon {
	BORDER-LEFT: #656565 6px solid; BORDER-RIGHT-WIDTH: 0px; LEFT: 0px
}
.z-calendar-over .z-calendar-left-icon {
	BORDER-RIGHT-COLOR: #5fa4ff
}
.z-calendar-over .z-calendar-right-icon {
	BORDER-LEFT-COLOR: #5fa4ff
}
.z-calendar-icon-disd .z-calendar-left-icon {
	CURSOR: auto; BORDER-RIGHT: #d9dada 6px solid
}
.z-calendar-icon-disd .z-calendar-right-icon {
	BORDER-LEFT: #d9dada 6px solid; CURSOR: auto
}
.z-calendar-icon-disd {
	CURSOR: default
}
.z-calendar-disd {
	FILTER: alpha(opacity=60); opacity: .6
}
.z-calendar-disd {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-calendar-disd * {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-datebox-rounded-calyear {
	BORDER-BOTTOM: #aca899 1px solid; BORDER-LEFT: #f8fbff 1px solid; BACKGROUND: #e9f1f3; BORDER-TOP: #f8fbff 1px solid; BORDER-RIGHT: #aca899 1px solid
}
.z-datebox-calyear {
	BORDER-BOTTOM: #aca899 1px solid; BORDER-LEFT: #f8fbff 1px solid; BACKGROUND: #e9f1f3; BORDER-TOP: #f8fbff 1px solid; BORDER-RIGHT: #aca899 1px solid
}
.z-datebox-rounded-calday {
	BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: #ddd 1px solid; BORDER-TOP: #ddd 1px solid; BORDER-RIGHT: #ddd 1px solid
}
.z-datebox-calday {
	BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: #ddd 1px solid; BORDER-TOP: #ddd 1px solid; BORDER-RIGHT: #ddd 1px solid
}
.z-datebox-timezone-body {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-datebox-timezone-item {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-calendar-calctrl TD {
	TEXT-ALIGN: center; WHITE-SPACE: nowrap; FONT-SIZE: 11px
}
.z-calendar-title {
	CURSOR: pointer
}
.z-calendar-calctrl .z-calendar-ctrler {
	CURSOR: pointer
}
.z-calendar-calyear TD {
	PADDING-BOTTOM: 12px; PADDING-LEFT: 3px; PADDING-RIGHT: 3px; PADDING-TOP: 12px
}
.z-calendar-calmon TD {
	PADDING-BOTTOM: 12px; PADDING-LEFT: 3px; PADDING-RIGHT: 3px; PADDING-TOP: 12px
}
.z-calendar-calday {
	TABLE-LAYOUT: fixed
}
.z-calendar-calyear TD {
	TEXT-ALIGN: center; COLOR: #35254f; FONT-SIZE: 11px; CURSOR: pointer; TEXT-DECORATION: none; -moz-user-select: none
}
.z-calendar-calmon TD {
	TEXT-ALIGN: center; COLOR: #35254f; FONT-SIZE: 11px; CURSOR: pointer; TEXT-DECORATION: none; -moz-user-select: none
}
.z-calendar-caldayrow TD {
	TEXT-ALIGN: center; COLOR: #35254f; FONT-SIZE: 11px; CURSOR: pointer; TEXT-DECORATION: none; -moz-user-select: none
}
.z-calendar-calyear TD {
	FONT-SIZE: 11px
}
.z-calendar-calmon TD {
	FONT-SIZE: 11px
}
.z-calendar-calday TD {
	PADDING-BOTTOM: 3px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 3px
}
.z-calendar-calyear .z-calendar-over {
	BACKGROUND: #f1f9fc; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-calendar-calmon .z-calendar-over {
	BACKGROUND: #f1f9fc; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-calendar-caldayrow .z-calendar-over {
	BACKGROUND: #f1f9fc; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-calendar-calyear TD.z-calendar-seld {
	BACKGROUND: #cce0fb; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-calendar-calmon TD.z-calendar-seld {
	BACKGROUND: #cce0fb; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-calendar-calday TD.z-calendar-seld {
	BACKGROUND: #cce0fb; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-datebox-rounded-calmon TD.z-datebox-rounded-over-seld {
	BACKGROUND: #5fa4ff; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-datebox-rounded-calday TD.z-datebox-rounded-over-seld {
	BACKGROUND: #5fa4ff; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-calendar TD.z-calendar-over-seld {
	BACKGROUND: #5fa4ff; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-datebox-calmon TD.z-datebox-over-seld {
	BACKGROUND: #5fa4ff; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-datebox-calday TD.z-datebox-over-seld {
	BACKGROUND: #5fa4ff; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-calendar TD.z-calendar-over-seld {
	COLOR: white
}
.z-calendar-caldow TD {
	TEXT-ALIGN: center
}
.z-datebox-rounded-caldow TD {
	TEXT-ALIGN: center; PADDING-BOTTOM: 1px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: #e8e8f0; COLOR: #333; FONT-SIZE: 11px; FONT-WEIGHT: bold; PADDING-TOP: 1px
}
.z-datebox-caldow TD {
	TEXT-ALIGN: center; PADDING-BOTTOM: 1px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: #e8e8f0; COLOR: #333; FONT-SIZE: 11px; FONT-WEIGHT: bold; PADDING-TOP: 1px
}
.z-calendar .z-weekend {
	COLOR: red
}
.z-calendar .z-outside {
	COLOR: #888
}
.z-datebox-rounded-calyear TD {
	COLOR: #35254f
}
.z-calendar-calyear TD {
	COLOR: #35254f
}
.z-datebox-calyear TD {
	COLOR: #35254f
}
.z-caption INPUT {
	FONT-SIZE: 11px
}
.z-caption TD {
	FONT-SIZE: 11px
}
.z-caption .z-caption-l {
	FONT-SIZE: 11px
}
.z-caption .z-caption-r {
	FONT-SIZE: 11px
}
.z-caption BUTTON {
	PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px; FONT-SIZE: 11px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
.z-caption .z-button .z-button-btn {
	PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px; FONT-SIZE: 11px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
.z-caption A {
	BACKGROUND: none transparent scroll repeat 0% 0%; COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-caption A:visited {
	BACKGROUND: none transparent scroll repeat 0% 0%; COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-caption .z-toolbar A {
	BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; COLOR: white; BORDER-LEFT-WIDTH: 0px
}
.z-caption .z-toolbar A:visited {
	BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; COLOR: white; BORDER-LEFT-WIDTH: 0px
}
.z-caption .z-toolbar A:hover {
	BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; COLOR: white; BORDER-LEFT-WIDTH: 0px
}
.z-caption A:hover {
	TEXT-DECORATION: underline
}
.z-textbox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-decimalbox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-intbox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-longbox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-doublebox-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-textbox-disd {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-textbox-disd * {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-decimalbox-disd {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-decimalbox-disd * {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-intbox-disd {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-intbox-disd * {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-longbox-disd {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-longbox-disd * {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-doublebox-disd {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-doublebox-disd * {
	COLOR: #aaa !important; CURSOR: default !important
}
.z-textbox {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px; -moz-border-radius: 2px; -webkit-border-radius: 2px
}
.z-decimalbox {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px; -moz-border-radius: 2px; -webkit-border-radius: 2px
}
.z-intbox {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px; -moz-border-radius: 2px; -webkit-border-radius: 2px
}
.z-longbox {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px; -moz-border-radius: 2px; -webkit-border-radius: 2px
}
.z-doublebox {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg.gif'))}) #fff repeat-x 0px 0px; FONT-SIZE: 11px; BORDER-TOP: #86a4be 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; border-radius: 2px; -moz-border-radius: 2px; -webkit-border-radius: 2px
}
.z-textbox-focus {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-decimalbox-focus {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-intbox-focus {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-longbox-focus {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-doublebox-focus {
	BORDER-BOTTOM: #90bce6 1px solid; BORDER-LEFT: #90bce6 1px solid; BORDER-TOP: #90bce6 1px solid; BORDER-RIGHT: #90bce6 1px solid
}
.z-textbox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-decimalbox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-intbox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-longbox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-doublebox-text-invalid {
	BORDER-BOTTOM: #dd7870 1px solid; BORDER-LEFT: #dd7870 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/text-bg-invalid.gif'))}) #fff repeat-x 0px 0px; BORDER-TOP: #dd7870 1px solid; BORDER-RIGHT: #dd7870 1px solid
}
.z-textbox-readonly {
	BACKGROUND: #eceae4
}
.z-textbox-text-disd {
	BACKGROUND: #eceae4
}
.z-intbox-readonly {
	BACKGROUND: #eceae4
}
.z-intbox-text-disd {
	BACKGROUND: #eceae4
}
.z-longbox-readonly {
	BACKGROUND: #eceae4
}
.z-longbox-text-disd {
	BACKGROUND: #eceae4
}
.z-doublebox-readonly {
	BACKGROUND: #eceae4
}
.z-doublebox-text-disd {
	BACKGROUND: #eceae4
}
.z-decimalbox-readonly {
	BACKGROUND: #eceae4
}
.z-decimalbox-text-disd {
	BACKGROUND: #eceae4
}
.z-textbox-inplace {
	PADDING-BOTTOM: 3px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 3px
}
.z-decimalbox-inplace {
	PADDING-BOTTOM: 3px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 3px
}
.z-intbox-inplace {
	PADDING-BOTTOM: 3px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 3px
}
.z-longbox-inplace {
	PADDING-BOTTOM: 3px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 3px
}
.z-doublebox-inplace {
	PADDING-BOTTOM: 3px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 3px
}
.z-textbox-inplace {
	PADDING-BOTTOM: 3px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; PADDING-TOP: 3px
}
.z-decimalbox-inplace {
	PADDING-BOTTOM: 3px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; PADDING-TOP: 3px
}
.z-intbox-inplace {
	PADDING-BOTTOM: 3px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; PADDING-TOP: 3px
}
.z-longbox-inplace {
	PADDING-BOTTOM: 3px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; PADDING-TOP: 3px
}
.z-doublebox-inplace {
	PADDING-BOTTOM: 3px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; PADDING-TOP: 3px
}
.z-textbox-disd * {
	FILTER: alpha(opacity=60)
}
.z-decimalbox-disd * {
	FILTER: alpha(opacity=60)
}
.z-intbox-disd * {
	FILTER: alpha(opacity=60)
}
.z-longbox-disd * {
	FILTER: alpha(opacity=60)
}
.z-doublebox-disd * {
	FILTER: alpha(opacity=60)
}
.z-textbox-rounded {
	DISPLAY: inline-block
}
.z-decimalbox-rounded {
	DISPLAY: inline-block
}
.z-intbox-rounded {
	DISPLAY: inline-block
}
.z-longbox-rounded {
	DISPLAY: inline-block
}
.z-doublebox-rounded {
	DISPLAY: inline-block
}
.z-textbox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) repeat-x 0px 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 5px
}
.z-decimalbox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) repeat-x 0px 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 5px
}
.z-intbox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) repeat-x 0px 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 5px
}
.z-longbox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) repeat-x 0px 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 5px
}
.z-doublebox-rounded-inp {
	PADDING-BOTTOM: 5px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; FONT-FAMILY: Tahoma; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) repeat-x 0px 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 14px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 5px
}
.z-textbox-rounded-right-edge {
	BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 5px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) no-repeat -19px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: default
}
.z-decimalbox-rounded-right-edge {
	BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 5px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) no-repeat -19px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: default
}
.z-intbox-rounded-right-edge {
	BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 5px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) no-repeat -19px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: default
}
.z-longbox-rounded-right-edge {
	BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 5px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) no-repeat -19px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: default
}
.z-doublebox-rounded-right-edge {
	BORDER-RIGHT-WIDTH: 0px; MARGIN-TOP: 1px; WIDTH: 5px; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}) no-repeat -19px -120px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 24px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; CURSOR: default
}
.z-textbox-rounded-disd {
	FILTER: alpha(opacity=60); FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal; opacity: .6; -moz-opacity: .6
}
.z-decimalbox-rounded-disd {
	FILTER: alpha(opacity=60); FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal; opacity: .6; -moz-opacity: .6
}
.z-intbox-rounded-disd {
	FILTER: alpha(opacity=60); FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal; opacity: .6; -moz-opacity: .6
}
.z-longbox-rounded-disd {
	FILTER: alpha(opacity=60); FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal; opacity: .6; -moz-opacity: .6
}
.z-doublebox-rounded-disd {
	FILTER: alpha(opacity=60); FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal; opacity: .6; -moz-opacity: .6
}
.z-textbox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-textbox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-decimalbox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-decimalbox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-intbox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-intbox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-longbox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-longbox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-doublebox-rounded-disd {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-doublebox-rounded-disd * {
	COLOR: #303030 !important; CURSOR: default !important
}
.z-textbox-rounded .z-textbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-decimalbox-rounded .z-decimalbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-intbox-rounded .z-intbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-longbox-rounded .z-longbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-doublebox-rounded .z-doublebox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-textbox-rounded .z-textbox-rounded-text-invalid + .z-textbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-decimalbox-rounded .z-decimalbox-rounded-text-invalid + .z-decimalbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-intbox-rounded .z-intbox-rounded-text-invalid + .z-intbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-longbox-rounded .z-longbox-rounded-text-invalid + .z-longbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-doublebox-rounded .z-doublebox-rounded-text-invalid + .z-doublebox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-textbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-decimalbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-intbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-longbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
I.z-doublebox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}); BACKGROUND-POSITION: 0px -24px
}
.z-textbox-rounded-real-readonly .z-textbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: 0px -72px
}
.z-decimalbox-rounded-real-readonly .z-decimalbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: 0px -72px
}
.z-intbox-rounded-real-readonly .z-intbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: 0px -72px
}
.z-longbox-rounded-real-readonly .z-longbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: 0px -72px
}
.z-doublebox-rounded-real-readonly .z-doublebox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: 0px -72px
}
.z-textbox-rounded-real-readonly .z-textbox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-decimalbox-rounded-real-readonly .z-decimalbox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-intbox-rounded-real-readonly .z-intbox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-longbox-rounded-real-readonly .z-longbox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-doublebox-rounded-real-readonly .z-doublebox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -192px
}
.z-textbox-rounded-focus .z-textbox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-decimalbox-rounded-focus .z-decimalbox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-intbox-rounded-focus .z-intbox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-longbox-rounded-focus .z-longbox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-doublebox-rounded-focus .z-doublebox-rounded-readonly {
	BACKGROUND-POSITION: 0px -96px
}
.z-textbox-rounded-focus .z-textbox-rounded-readonly + .z-textbox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-decimalbox-rounded-focus .z-decimalbox-rounded-readonly + .z-decimalbox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-intbox-rounded-focus .z-intbox-rounded-readonly + .z-intbox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-longbox-rounded-focus .z-longbox-rounded-readonly + .z-longbox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-doublebox-rounded-focus .z-doublebox-rounded-readonly + .z-doublebox-rounded-right-edge {
	BACKGROUND-POSITION: -19px -216px
}
.z-textbox-rounded-focus I.z-textbox-rounded-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-decimalbox-rounded-focus I.z-decimalbox-rounded-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-intbox-rounded-focus I.z-intbox-rounded-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-longbox-rounded-focus I.z-longbox-rounded-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-doublebox-rounded-focus I.z-doublebox-rounded-right-edge-readonly {
	BACKGROUND-POSITION: -19px -216px
}
.z-textbox-rounded-focus INPUT.z-textbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); OUTLINE-STYLE: none; BACKGROUND-POSITION: 0px 0px
}
.z-decimalbox-rounded-focus INPUT.z-decimalbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); OUTLINE-STYLE: none; BACKGROUND-POSITION: 0px 0px
}
.z-intbox-rounded-focus INPUT.z-intbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); OUTLINE-STYLE: none; BACKGROUND-POSITION: 0px 0px
}
.z-longbox-rounded-focus INPUT.z-longbox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); OUTLINE-STYLE: none; BACKGROUND-POSITION: 0px 0px
}
.z-doublebox-rounded-focus INPUT.z-doublebox-rounded-inp {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); OUTLINE-STYLE: none; BACKGROUND-POSITION: 0px 0px
}
.z-textbox-rounded-focus I.z-textbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: -19px -120px
}
.z-decimalbox-rounded-focus I.z-decimalbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: -19px -120px
}
.z-intbox-rounded-focus I.z-intbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: -19px -120px
}
.z-longbox-rounded-focus I.z-longbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: -19px -120px
}
.z-doublebox-rounded-focus I.z-doublebox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/timebox-rounded.gif'))}); BACKGROUND-POSITION: -19px -120px
}
.z-textbox-rounded-focus INPUT.z-textbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-decimalbox-rounded-focus INPUT.z-decimalbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-intbox-rounded-focus INPUT.z-intbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-longbox-rounded-focus INPUT.z-longbox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-doublebox-rounded-focus INPUT.z-doublebox-rounded-text-invalid {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) repeat-x 0px 0px
}
.z-textbox-rounded-focus INPUT.z-textbox-rounded-text-invalid + I.z-textbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-decimalbox-rounded-focus INPUT.z-decimalbox-rounded-text-invalid + I.z-decimalbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-intbox-rounded-focus INPUT.z-intbox-rounded-text-invalid + I.z-intbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-longbox-rounded-focus INPUT.z-longbox-rounded-text-invalid + I.z-longbox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-doublebox-rounded-focus INPUT.z-doublebox-rounded-text-invalid + I.z-doublebox-rounded-right-edge {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-textbox-rounded-focus .z-textbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-decimalbox-rounded-focus .z-decimalbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-intbox-rounded-focus .z-intbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-longbox-rounded-focus .z-longbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-doublebox-rounded-focus .z-doublebox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/button/redcombo-rounded.gif'))}) !important; BACKGROUND-POSITION: 0px -24px
}
.z-textbox-rounded-inplace .z-textbox-rounded-inp {
	BACKGROUND-IMAGE: none
}
.z-decimalbox-rounded-inplace .z-decimalbox-rounded-inp {
	BACKGROUND-IMAGE: none
}
.z-intbox-rounded-inplace .z-intbox-rounded-inp {
	BACKGROUND-IMAGE: none
}
.z-longbox-rounded-inplace .z-longbox-rounded-inp {
	BACKGROUND-IMAGE: none
}
.z-doublebox-rounded-inplace .z-doublebox-rounded-inp {
	BACKGROUND-IMAGE: none
}
.z-textbox-rounded-inplace .z-textbox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-decimalbox-rounded-inplace .z-decimalbox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-intbox-rounded-inplace .z-intbox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-longbox-rounded-inplace .z-longbox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-doublebox-rounded-inplace .z-doublebox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-textbox-rounded-inplace INPUT.z-textbox-rounded-text-invalid {
	BACKGROUND-IMAGE: none
}
.z-decimalbox-rounded-inplace INPUT.z-decimalbox-rounded-text-invalid {
	BACKGROUND-IMAGE: none
}
.z-intbox-rounded-inplace INPUT.z-intbox-rounded-text-invalid {
	BACKGROUND-IMAGE: none
}
.z-longbox-rounded-inplace INPUT.z-longbox-rounded-text-invalid {
	BACKGROUND-IMAGE: none
}
.z-doublebox-rounded-inplace INPUT.z-doublebox-rounded-text-invalid {
	BACKGROUND-IMAGE: none
}
.z-textbox-rounded-inplace INPUT.z-textbox-rounded-text-invalid + I.z-textbox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-decimalbox-rounded-inplace INPUT.z-decimalbox-rounded-text-invalid + I.z-decimalbox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-intbox-rounded-inplace INPUT.z-intbox-rounded-text-invalid + I.z-intbox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-longbox-rounded-inplace INPUT.z-longbox-rounded-text-invalid + I.z-longbox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-doublebox-rounded-inplace INPUT.z-doublebox-rounded-text-invalid + I.z-doublebox-rounded-right-edge {
	BACKGROUND-IMAGE: none
}
.z-textbox-rounded-inplace I.z-textbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: none
}
.z-decimalbox-rounded-inplace I.z-decimalbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: none
}
.z-intbox-rounded-inplace I.z-intbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: none
}
.z-longbox-rounded-inplace I.z-longbox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: none
}
.z-doublebox-rounded-inplace I.z-doublebox-rounded-right-edge-invalid {
	BACKGROUND-IMAGE: none
}
.z-errbox {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-errbox-center {
	PADDING-BOTTOM: 2px; PADDING-LEFT: 3px; PADDING-RIGHT: 3px; PADDING-TOP: 2px
}
.z-errbox-left {
	BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 17px; BACKGROUND-REPEAT: no-repeat; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
}
.z-errbox-right {
	BORDER-RIGHT-WIDTH: 0px; PADDING-RIGHT: 17px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: right 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
}
.z-arrow-d {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/arrowD.png'))})
}
.z-arrow-l {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/arrowL.png'))})
}
.z-arrow-ld {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/arrowLD.png'))})
}
.z-arrow-lu {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/arrowLU.png'))})
}
.z-arrow-rd {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/arrowRD.png'))})
}
.z-arrow-ru {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/arrowRU.png'))})
}
.z-arrow-r {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/arrowR.png'))})
}
.z-arrow-u {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/arrowU.png'))})
}
.z-errbox-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/close.gif'))}); ZOOM: 1
}
.z-errbox-close-over {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/close-over.gif'))})
}
.z-popup.z-errbox .z-popup-tl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/pp-corner.png'))})
}
.z-popup.z-errbox .z-popup-tr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/pp-corner.png'))})
}
.z-popup.z-errbox .z-popup-bl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/pp-corner.png'))})
}
.z-popup.z-errbox .z-popup-br {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/pp-corner.png'))})
}
.z-popup.z-errbox .z-popup-cm {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/pp-cm.png'))}); BACKGROUND-COLOR: #fdf2e7
}
.z-popup.z-errbox .z-popup-cl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/pp-clr.png'))})
}
.z-popup.z-errbox .z-popup-cr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/errbox/pp-clr.png'))})
}
.z-frozen {
	OVERFLOW: hidden
}
.z-frozen-body {
	OVERFLOW: hidden
}
.z-frozen-inner {
	OVERFLOW: hidden
}
.z-frozen {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/bar-bg.png'))})
}
.z-frozen-body {
	FLOAT: left
}
.z-frozen-inner {
	OVERFLOW-X: scroll; FLOAT: right
}
.z-frozen-inner DIV {
	HEIGHT: 100%
}
DIV.z-grid {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; ZOOM: 1; BACKGROUND: #dae7f6; OVERFLOW: hidden; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid
}
DIV.z-grid-header {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-grid-header TR {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-grid-footer {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-grid-header {
	OVERFLOW: hidden
}
DIV.z-grid-footer {
	OVERFLOW: hidden
}
DIV.z-grid-header TR.z-columns {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))}); BACKGROUND-COLOR: #c3e7fb; BACKGROUND-REPEAT: repeat-x
}
DIV.z-grid-header TR.z-auxhead {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))}); BACKGROUND-COLOR: #c3e7fb; BACKGROUND-REPEAT: repeat-x
}
DIV.z-grid-header TH.z-column {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-grid-header TH.z-auxheader {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-grid-header .z-column-sort DIV.z-column-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_hint.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-grid-header .z-column-sort-asc DIV.z-column-cnt {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_asc.gif'))}); BACKGROUND-COLOR: transparent; PADDING-RIGHT: 9px; BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 99% center; CURSOR: pointer
}
DIV.z-grid-header .z-column-sort-asc {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-over.png'))}) #ddeefb repeat-x 0px 0px
}
DIV.z-grid-header .z-column-sort-dsc {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-over.png'))}) #ddeefb repeat-x 0px 0px
}
DIV.z-grid-header .z-column-sort-dsc DIV.z-column-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_dsc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-grid-body {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-grid-pgi-b {
	WIDTH: 100%; OVERFLOW: hidden; BORDER-TOP: #aab 1px solid
}
DIV.z-grid-pgi-t {
	BORDER-BOTTOM: #aab 1px solid; WIDTH: 100%; OVERFLOW: hidden
}
DIV.z-grid-footer {
	BACKGROUND: #dae7f6; BORDER-TOP: #9eb6ce 1px solid
}
DIV.z-footer-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-row-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-group-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-groupfoot-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-column-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-row-cnt {
	PADDING-BOTTOM: 1px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 1px
}
DIV.z-footer-cnt {
	OVERFLOW: hidden; CURSOR: default
}
DIV.z-column-cnt {
	OVERFLOW: hidden; CURSOR: default
}
.z-word-wrap DIV.z-row-cnt {
	WORD-WRAP: break-word
}
.z-word-wrap DIV.z-group-cnt {
	WORD-WRAP: break-word
}
.z-word-wrap DIV.z-groupfoot-cnt {
	WORD-WRAP: break-word
}
.z-word-wrap DIV.z-footer-cnt {
	WORD-WRAP: break-word
}
.z-word-wrap DIV.z-column-cnt {
	WORD-WRAP: break-word
}
TR.z-grid-faker {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-grid-faker TH {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-grid-faker DIV {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
.z-cell {
	PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; OVERFLOW: hidden; PADDING-TOP: 2px
}
TD.z-row-inner {
	PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; OVERFLOW: hidden; PADDING-TOP: 2px
}
TD.z-groupfoot-inner {
	PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; OVERFLOW: hidden; PADDING-TOP: 2px
}
DIV.z-row-cnt {
	COLOR: black
}
TR.z-row TD.z-row-inner {
	BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: white 1px solid; BORDER-TOP-STYLE: none; BACKGROUND: white; BORDER-RIGHT: #ccc 1px solid
}
TR.z-row .z-cell {
	BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: white 1px solid; BORDER-TOP-STYLE: none; BACKGROUND: white; BORDER-RIGHT: #ccc 1px solid
}
TR.z-grid-odd TD.z-row-inner {
	BACKGROUND: #f0faff
}
TR.z-grid-odd .z-cell {
	BACKGROUND: #f0faff
}
TR.z-grid-odd {
	BACKGROUND: #f0faff
}
TR.z-group {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/group_bg.gif'))}) #e9f2fb repeat-x 0px 0px
}
TD.z-group-inner {
	BORDER-BOTTOM: #bcd2ef 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-FAMILY: Tahoma; COLOR: #2c559c; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #81baf5 2px solid; FONT-WEIGHT: bold; PADDING-TOP: 2px
}
.z-group-inner .z-group-cnt .z-label {
	PADDING-BOTTOM: 4px; PADDING-LEFT: 2px; WIDTH: auto; PADDING-RIGHT: 2px; FONT-FAMILY: Tahoma; COLOR: #2c559c; FONT-SIZE: 11px; FONT-WEIGHT: bold; PADDING-TOP: 4px
}
.z-group-inner .z-group-cnt {
	PADDING-BOTTOM: 4px; PADDING-LEFT: 2px; WIDTH: auto; PADDING-RIGHT: 2px; FONT-FAMILY: Tahoma; COLOR: #2c559c; FONT-SIZE: 11px; FONT-WEIGHT: bold; PADDING-TOP: 4px
}
.z-group-img {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/toggle.gif'))}); BORDER-RIGHT-WIDTH: 0px; MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 100%; VERTICAL-ALIGN: top; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
}
.z-group-img-open {
	BACKGROUND-POSITION: 0px -18px
}
.z-group-img-close {
	BACKGROUND-POSITION: 0px 0px
}
.z-groupfoot {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/groupfoot_bg.gif'))}) #e9f2fb repeat-x 0px 0px
}
.z-groupfoot-inner .z-groupfoot-cnt .z-label {
	FONT-FAMILY: Tahoma; COLOR: #2c559c; FONT-SIZE: 11px; FONT-WEIGHT: bold
}
.z-groupfoot-inner .z-groupfoot-cnt {
	FONT-FAMILY: Tahoma; COLOR: #2c559c; FONT-SIZE: 11px; FONT-WEIGHT: bold
}
.z-column .z-column-cnt {
	POSITION: relative
}
.z-column-btn {
	Z-INDEX: 15; POSITION: absolute; WIDTH: 14px; DISPLAY: none; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/hd-btn.png'))}) no-repeat left center; TOP: 0px; CURSOR: pointer; RIGHT: 0px
}
.z-column-over .z-column-btn {
	DISPLAY: block
}
.z-column-visi .z-column-btn {
	DISPLAY: block
}
A.z-column-btn:hover {
	BACKGROUND-POSITION: -14px center
}
.z-column-over {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-over.png'))}) #acddf9 repeat-x 0px 0px
}
.z-columns-menu-grouping .z-menu-item-img {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/menu-group.png'))})
}
.z-columns-menu-asc .z-menu-item-img {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/menu-arrowup.png'))})
}
.z-columns-menu-dsc .z-menu-item-img {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/menu-arrowdown.png'))})
}
DIV.z-grid-header .z-column-sizing.z-column {
	CURSOR: e-resize
}
DIV.z-grid-header .z-column-sizing.z-column DIV.z-column-cnt {
	CURSOR: e-resize
}
DIV.z-grid-header {
	POSITION: relative
}
DIV.z-grid-footer {
	POSITION: relative
}
DIV.z-grid-header TH.z-column {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-grid-header TH.z-auxheader {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-column-cnt {
	WHITE-SPACE: nowrap
}
.z-auxheader-cnt {
	WHITE-SPACE: nowrap
}
DIV.z-footer-cnt {
	POSITION: relative
}
DIV.z-row-cnt {
	POSITION: relative
}
DIV.z-group-cnt {
	POSITION: relative
}
DIV.z-groupfoot-cnt {
	POSITION: relative
}
DIV.z-column-cnt {
	POSITION: relative
}
.z-auxheader-cnt {
	POSITION: relative
}
DIV.z-footer-cnt {
	WIDTH: 100%
}
DIV.z-row-cnt {
	WIDTH: 100%
}
DIV.z-group-cnt {
	WIDTH: 100%
}
DIV.z-groupfoot-cnt {
	WIDTH: 100%
}
DIV.z-grid-body {
	POSITION: relative
}
TR.z-grid-faker {
	POSITION: absolute; TOP: -1000px; LEFT: -1000px
}
.z-fieldset LEGEND {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-fieldset-cnt {
	OVERFLOW: hidden
}
.z-fieldset-colpsd {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; BORDER-TOP-WIDTH: 2px !important; BORDER-BOTTOM-WIDTH: 0px !important; BORDER-LEFT-WIDTH: 0px !important
}
.z-fieldset-colpsd .z-fieldset-cnt {
	POSITION: absolute; TOP: -1000px; LEFT: -1000px
}
.z-fieldset .z-caption {
	CURSOR: pointer
}
.z-fieldset .z-caption-readonly {
	CURSOR: default
}
.z-groupbox {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; OVERFLOW: hidden; PADDING-TOP: 0px
}
.z-groupbox-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/groupbox/groupbox-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-groupbox-tr {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/groupbox/groupbox-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-groupbox-tr {
	POSITION: relative; BACKGROUND-POSITION: right -5px; MARGIN-RIGHT: -5px
}
.z-groupbox-hl {
	BORDER-BOTTOM: #b2ccd9 1px solid; PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/groupbox/groupbox-hl.png'))}) repeat-y 0px 0px
}
.z-groupbox-hr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/groupbox/groupbox-hr.png'))}) repeat-y right 0px
}
.z-groupbox-hm {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/groupbox/groupbox-hm.png'))}) repeat-x 0px 0px; OVERFLOW: hidden
}
.z-groupbox-hm {
	CURSOR: pointer
}
.z-groupbox-hm-readonly {
	CURSOR: default
}
.z-groupbox-header {
	ZOOM: 1; OVERFLOW: hidden
}
.z-groupbox-hl .z-groupbox-header {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 4px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BACKGROUND: none transparent scroll repeat 0% 0%; COLOR: #373737; FONT-SIZE: 11px; BORDER-TOP: 0px; FONT-WEIGHT: normal; BORDER-RIGHT: 0px; PADDING-TOP: 0px
}
.z-groupbox-cnt {
	BORDER-BOTTOM: #b2ccd9 1px solid; BORDER-LEFT: #b2ccd9 1px solid; PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; ZOOM: 1; BORDER-TOP: #b2ccd9 1px solid; BORDER-RIGHT: #b2ccd9 1px solid; PADDING-TOP: 5px
}
.z-groupbox-bl {
	LINE-HEIGHT: 0; PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/img/shdlf.gif'))}) no-repeat 0px bottom; HEIGHT: 6px; FONT-SIZE: 0px
}
.z-groupbox-br {
	LINE-HEIGHT: 0; PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/img/shdrg.gif'))}) no-repeat right bottom; HEIGHT: 6px; FONT-SIZE: 0px
}
.z-groupbox-bm {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/img/shdmd.gif'))}) repeat-x 0px 0px; HEIGHT: 6px; FONT-SIZE: 0px
}
.z-hlayout {
	POSITION: relative; OVERFLOW: hidden
}
.z-vlayout {
	POSITION: relative; OVERFLOW: hidden
}
.z-hlayout {
	WHITE-SPACE: nowrap
}
.z-hlayout-inner {
	POSITION: relative; ZOOM: 1; DISPLAY: inline; VERTICAL-ALIGN: middle
}
.z-valign-bottom > .z-hlayout-inner {
	VERTICAL-ALIGN: bottom
}
.z-valign-top > .z-hlayout-inner {
	VERTICAL-ALIGN: top
}
.z-valign-middle > .z-hlayout-inner {
	VERTICAL-ALIGN: middle
}
.z-vlayout-inner {
	POSITION: relative; ZOOM: 1
}
DIV.z-listbox {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; ZOOM: 1; BACKGROUND: #dae7f6; OVERFLOW: hidden; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid
}
DIV.z-listbox-header {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-listbox-header TR {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-listbox-footer {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-listbox-header {
	OVERFLOW: hidden
}
DIV.z-listbox-footer {
	OVERFLOW: hidden
}
DIV.z-listbox-header TR.z-listhead {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))}) #c7e5f1 repeat-x 0px 0px
}
DIV.z-listbox-header TR.z-auxhead {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))}) #c7e5f1 repeat-x 0px 0px
}
DIV.z-listbox-header TH.z-listheader {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-listbox-header TH.z-auxheader {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-listbox-header TH.z-listheader-sort DIV.z-listheader-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_hint.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-listbox-header TH.z-listheader-sort-asc DIV.z-listheader-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_asc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-listbox-header TH.z-listheader-sort-dsc DIV.z-listheader-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_dsc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-listbox-body {
	POSITION: relative; BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-listbox-pgi-b {
	OVERFLOW: hidden; BORDER-TOP: #aab 1px solid
}
DIV.z-listbox-pgi-t {
	BORDER-BOTTOM: #aab 1px solid; OVERFLOW: hidden
}
DIV.z-listbox-body .z-listcell {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-listbox-footer .z-listfooter {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-listbox-body .z-listgroupfoot-inner {
	CURSOR: default
}
DIV.z-listbox-body .z-listgroup-inner {
	CURSOR: default
}
DIV.z-listbox-footer {
	BACKGROUND: #dae7f6; BORDER-TOP: #9eb6ce 1px solid
}
DIV.z-listfooter-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-listcell-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-listheader-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-listcell-cnt {
	PADDING-BOTTOM: 1px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 1px
}
DIV.z-listfooter-cnt {
	OVERFLOW: hidden; CURSOR: default
}
DIV.z-listheader-cnt {
	OVERFLOW: hidden; CURSOR: default
}
.z-word-wrap DIV.z-listcell-cnt {
	WORD-WRAP: break-word
}
.z-word-wrap DIV.z-listfooter-cnt {
	WORD-WRAP: break-word
}
.z-word-wrap DIV.z-listheader-cnt {
	WORD-WRAP: break-word
}
TR.z-listbox-faker {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-listbox-faker TH {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-listbox-faker DIV {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-listitem {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
TR.z-listitem A {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
TR.z-listitem A:visited {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
TR.z-listitem A:hover {
	TEXT-DECORATION: underline
}
TR.z-listbox-odd {
	BACKGROUND: #e6f8ff
}
TR.z-listitem-disd * {
	COLOR: #c5cacb !important; CURSOR: default !important
}
TD.z-listcell-disd * {
	COLOR: #c5cacb !important; CURSOR: default !important
}
TR.z-listitem-disd A:visited {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
TR.z-listitem-disd A:hover {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
TD.z-listcell-disd A:visited {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
TD.z-listcell-disd A:hover {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
TR.z-listitem-seld {
	BORDER-BOTTOM: #6f97d2 1px solid; BORDER-LEFT: #6f97d2 1px solid; BACKGROUND: #b3c8e8; BORDER-TOP: #6f97d2 1px solid; BORDER-RIGHT: #6f97d2 1px solid
}
TR.z-listitem-over {
	BACKGROUND: #dae7f6
}
TR.z-listitem-over-seld {
	BACKGROUND: #6eadff
}
TR.z-listitem TD.z-listitem-focus {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/focusd.png'))}); BACKGROUND-REPEAT: no-repeat
}
TR.z-listgroup {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/group_bg.gif'))}) #e9f2fb repeat-x 0px 0px
}
TD.z-listgroup-inner {
	BORDER-BOTTOM: #bcd2ef 1px solid; BORDER-TOP: #81baf5 2px solid; PADDING-TOP: 2px
}
TD.z-listgroup-inner DIV.z-listcell-cnt {
	PADDING-BOTTOM: 4px; PADDING-LEFT: 2px; WIDTH: auto; PADDING-RIGHT: 2px; FONT-FAMILY: Tahoma; COLOR: #2c559c; FONT-SIZE: 11px; FONT-WEIGHT: bold; PADDING-TOP: 4px
}
.z-listgroup-img {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/toggle.gif'))}); BORDER-RIGHT-WIDTH: 0px; MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 100%; VERTICAL-ALIGN: top; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
}
.z-listgroup-img-open {
	BACKGROUND-POSITION: 0px -18px
}
.z-listgroup-img-close {
	BACKGROUND-POSITION: 0px 0px
}
.z-listgroupfoot {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/groupfoot_bg.gif'))}) #e9f2fb repeat-x 0px 0px
}
TD.z-listgroupfoot-inner DIV.z-listcell-cnt {
	FONT-FAMILY: Tahoma; COLOR: #2c559c; FONT-SIZE: 11px; FONT-WEIGHT: bold
}
.z-listbox-header .z-listheader-sizing.z-listheader {
	CURSOR: e-resize
}
.z-listbox-header .z-listheader-sizing.z-listheader .z-listheader-cnt {
	CURSOR: e-resize
}
DIV.z-listbox-header {
	POSITION: relative
}
DIV.z-listbox-footer {
	POSITION: relative
}
DIV.z-listbox-header TH.z-listheader {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-listbox-header TH.z-auxheader {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-listheader-cnt {
	WHITE-SPACE: nowrap
}
.z-auxheader-cnt {
	WHITE-SPACE: nowrap
}
DIV.z-listfooter-cnt {
	POSITION: relative
}
DIV.z-listcell-cnt {
	POSITION: relative
}
DIV.z-listheader-cnt {
	POSITION: relative
}
.z-auxheader-cnt {
	POSITION: relative
}
DIV.z-listfooter-cnt {
	WIDTH: 100%
}
DIV.z-listcell-cnt {
	WIDTH: 100%
}
DIV.z-listbox-body {
	POSITION: relative
}
TR.z-listbox-faker {
	POSITION: absolute; TOP: -1000px; LEFT: -1000px
}
.z-listitem-img {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 2px; MIN-HEIGHT: 13px; PADDING-LEFT: 0px; WIDTH: 13px; PADDING-RIGHT: 0px; DISPLAY: inline-block; BACKGROUND: no-repeat center center; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 13px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-listheader-img {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 2px; MIN-HEIGHT: 13px; PADDING-LEFT: 0px; WIDTH: 13px; PADDING-RIGHT: 0px; DISPLAY: inline-block; BACKGROUND: no-repeat center center; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 13px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-listgroup-img-checkbox {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 2px; MIN-HEIGHT: 13px; PADDING-LEFT: 0px; WIDTH: 13px; PADDING-RIGHT: 0px; DISPLAY: inline-block; BACKGROUND: no-repeat center center; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 13px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-listgroupfoot-img {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 2px; MIN-HEIGHT: 13px; PADDING-LEFT: 0px; WIDTH: 13px; PADDING-RIGHT: 0px; DISPLAY: inline-block; BACKGROUND: no-repeat center center; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 13px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-listheader-img {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/check-sprite.gif'))}); BACKGROUND-POSITION: 0px 0px
}
.z-listitem-img-checkbox {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/check-sprite.gif'))}); BACKGROUND-POSITION: 0px 0px
}
.z-listitem-img-radio {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/check-sprite.gif'))}); BACKGROUND-POSITION: 0px 0px
}
.z-listgroup-img-checkbox {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/check-sprite.gif'))}); BACKGROUND-POSITION: 0px 0px
}
.z-listgroupfoot-img {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/check-sprite.gif'))}); BACKGROUND-POSITION: 0px 0px
}
.z-listitem-img-radio {
	BACKGROUND-POSITION: 0px -13px
}
.z-listitem-over .z-listitem-img-radio {
	BACKGROUND-POSITION: -13px -13px
}
.z-listitem-seld .z-listitem-img-radio {
	BACKGROUND-POSITION: -26px -13px
}
.z-listitem-over-seld .z-listitem-img-radio {
	BACKGROUND-POSITION: -39px -13px
}
.z-listheader-img-over {
	BACKGROUND-POSITION: -13px 0px
}
.z-listitem-over .z-listitem-img-checkbox {
	BACKGROUND-POSITION: -13px 0px
}
.z-listgroup-over .z-listgroup-img-checkbox {
	BACKGROUND-POSITION: -13px 0px
}
.z-listgroupfoot-over .z-listgroupfoot-img-checkbox {
	BACKGROUND-POSITION: -13px 0px
}
.z-listheader-img-seld {
	BACKGROUND-POSITION: -26px 0px
}
.z-listitem-seld .z-listitem-img-checkbox {
	BACKGROUND-POSITION: -26px 0px
}
.z-listgroup-seld .z-listgroup-img-checkbox {
	BACKGROUND-POSITION: -26px 0px
}
.z-listgroupfoot-seld .z-listgroupfoot-img-checkbox {
	BACKGROUND-POSITION: -26px 0px
}
.z-listheader-img-over-seld {
	BACKGROUND-POSITION: -39px 0px
}
.z-listitem-over-seld .z-listitem-img-checkbox {
	BACKGROUND-POSITION: -39px 0px
}
.z-listgroup-over-seld .z-listgroup-img-checkbox {
	BACKGROUND-POSITION: -39px 0px
}
.z-listgroupfoot-over-seld .z-listgroupfoot-img-checkbox {
	BACKGROUND-POSITION: -39px 0px
}
.z-listitem-img-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-menubar-hor .z-menu {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-hor .z-menu-item {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-hor .z-menu-btn {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-hor .z-menu-item-btn {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-hor SPAN {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-hor A {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-hor DIV {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-ver .z-menu {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-ver .z-menu-item {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-ver .z-menu-btn {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-ver .z-menu-item-btn {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-ver SPAN {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-ver A {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-ver DIV {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menu-cnt {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menu-item-cnt {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-menubar-hor {
	BORDER-BOTTOM: #b1cbd5 0px solid; POSITION: relative; PADDING-BOTTOM: 2px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; BACKGROUND: 0px center; PADDING-TOP: 2px
}
.z-menubar-ver {
	BORDER-BOTTOM: #b1cbd5 1px solid; POSITION: relative; PADDING-BOTTOM: 2px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; BACKGROUND: 0px center; PADDING-TOP: 2px
}
.z-menubar-hor .z-menu {
	VERTICAL-ALIGN: middle
}
.z-menubar-hor .z-menu-item {
	VERTICAL-ALIGN: middle
}
.z-menubar-ver .z-menu {
	VERTICAL-ALIGN: middle
}
.z-menubar-ver .z-menu-item {
	VERTICAL-ALIGN: middle
}
.z-menu-cnt {
	TEXT-DECORATION: none
}
.z-menu-item-cnt {
	TEXT-DECORATION: none
}
.z-menubar-hor .z-menu-body {
	CURSOR: pointer
}
.z-menubar-hor .z-menu-item-body {
	CURSOR: pointer
}
.z-menubar-ver .z-menu-body {
	CURSOR: pointer
}
.z-menubar-ver .z-menu-item-body {
	CURSOR: pointer
}
.z-menubar-hor .z-menu-body .z-menu-inner-l {
	LINE-HEIGHT: 0; WIDTH: 3px; HEIGHT: 21px; FONT-SIZE: 0px
}
.z-menubar-hor .z-menu-body .z-menu-inner-r {
	LINE-HEIGHT: 0; WIDTH: 3px; HEIGHT: 21px; FONT-SIZE: 0px
}
.z-menubar-hor .z-menu-item-body .z-menu-item-inner-l {
	LINE-HEIGHT: 0; WIDTH: 3px; HEIGHT: 21px; FONT-SIZE: 0px
}
.z-menubar-hor .z-menu-item-body .z-menu-item-inner-r {
	LINE-HEIGHT: 0; WIDTH: 3px; HEIGHT: 21px; FONT-SIZE: 0px
}
.z-menubar-ver .z-menu-body .z-menu-inner-l {
	LINE-HEIGHT: 0; WIDTH: 3px; HEIGHT: 21px; FONT-SIZE: 0px
}
.z-menubar-ver .z-menu-body .z-menu-inner-r {
	LINE-HEIGHT: 0; WIDTH: 3px; HEIGHT: 21px; FONT-SIZE: 0px
}
.z-menubar-ver .z-menu-item-body .z-menu-item-inner-l {
	LINE-HEIGHT: 0; WIDTH: 3px; HEIGHT: 21px; FONT-SIZE: 0px
}
.z-menubar-ver .z-menu-item-body .z-menu-item-inner-r {
	LINE-HEIGHT: 0; WIDTH: 3px; HEIGHT: 21px; FONT-SIZE: 0px
}
.z-menu-inner-l .z-menu-space {
	WIDTH: 3px; DISPLAY: block
}
.z-menu-inner-r .z-menu-space {
	WIDTH: 3px; DISPLAY: block
}
.z-menu-item-inner-l .z-menu-item-space {
	WIDTH: 3px; DISPLAY: block
}
.z-menu-item-inner-r .z-menu-item-space {
	WIDTH: 3px; DISPLAY: block
}
.z-menubar-hor .z-menu-body .z-menu-inner-m {
	TEXT-ALIGN: center; HEIGHT: 21px
}
.z-menubar-hor .z-menu-item-body .z-menu-item-inner-m {
	TEXT-ALIGN: center; HEIGHT: 21px
}
.z-menubar-ver .z-menu-body .z-menu-inner-m {
	TEXT-ALIGN: center; HEIGHT: 21px
}
.z-menubar-ver .z-menu-item-body .z-menu-item-inner-m {
	TEXT-ALIGN: center; HEIGHT: 21px
}
.z-menu-body .z-menu-inner-m DIV {
	MIN-HEIGHT: 16px; PADDING-LEFT: 0px; PADDING-RIGHT: 3px; DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/btn-arrow.gif'))}) no-repeat right -14px
}
.z-menubar-ver .z-menu-body .z-menu-inner-m DIV {
	BACKGROUND-POSITION: right 0px
}
.z-menu-item-body .z-menu-item-inner-m DIV {
	BACKGROUND-COLOR: transparent; MIN-HEIGHT: 16px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block
}
.z-menu-inner-m .z-menu-btn {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; FONT-WEIGHT: bold;  PADDING-BOTTOM: 2px; MARGIN: 0px; OUTLINE-STYLE: none; MIN-HEIGHT: 13px; PADDING-LEFT: 2px; OUTLINE-WIDTH: 0px; WIDTH: auto; PADDING-RIGHT: 1px; BACKGROUND: no-repeat 0px 2px; OVERFLOW: visible; BORDER-TOP: 0px; CURSOR: pointer; BORDER-RIGHT: 0px; TEXT-DECORATION: none; PADDING-TOP: 3px
}
.z-menu-item-inner-m .z-menu-item-btn {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; FONT-WEIGHT: bold; PADDING-BOTTOM: 2px; MARGIN: 0px; OUTLINE-STYLE: none; MIN-HEIGHT: 13px; PADDING-LEFT: 2px; OUTLINE-WIDTH: 0px; WIDTH: auto; PADDING-RIGHT: 1px; BACKGROUND: no-repeat 0px 2px; OVERFLOW: visible; BORDER-TOP: 0px; CURSOR: pointer; BORDER-RIGHT: 0px; TEXT-DECORATION: none; PADDING-TOP: 3px
}
.z-menu-body-text-img .z-menu-inner-m .z-menu-btn {
	PADDING-LEFT: 18px
}
.z-menu-body-img .z-menu-inner-m .z-menu-btn {
	PADDING-LEFT: 18px
}
.z-menu-item-body-text-img .z-menu-item-inner-m .z-menu-item-btn {
	PADDING-LEFT: 18px
}
.z-menu-item-body-img .z-menu-item-inner-m .z-menu-item-btn {
	PADDING-LEFT: 12px; PADDING-RIGHT: 0px
}
.z-menu-item-body-text .z-menu-item-inner-m .z-menu-item-btn {
	PADDING-LEFT: 12px; PADDING-RIGHT: 0px
}
.z-menu-inner-m .z-menu-btn {
	PADDING-RIGHT: 4px
}
.z-menu-item-body-img .z-menu-item-inner-m .z-menu-item-btn {
	PADDING-RIGHT: 0px
}
.z-menu-item-body-text .z-menu-item-inner-m .z-menu-item-btn {
	PADDING-RIGHT: 0px
}
.z-menu-body-over .z-menu-inner-l {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 0px 0px
}
.z-menu-body-seld .z-menu-inner-l {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 0px 0px
}
.z-menu-item-body-over .z-menu-item-inner-l {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 0px 0px
}
.z-menu-body-over .z-menu-inner-r {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 0px 0px
}
.z-menu-body-seld .z-menu-inner-r {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 0px 0px
}
.z-menu-item-body-over .z-menu-item-inner-r {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: no-repeat; BACKGROUND-POSITION: 0px 0px
}
.z-menu-body-over .z-menu-inner-r {
	BACKGROUND-POSITION: 0px -41px
}
.z-menu-body-seld .z-menu-inner-r {
	BACKGROUND-POSITION: 0px -41px
}
.z-menu-item-body-over .z-menu-item-inner-r {
	BACKGROUND-POSITION: 0px -41px
}
.z-menu-body-over .z-menu-inner-m {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: repeat-x; BACKGROUND-POSITION: 0px -82px
}
.z-menu-body-seld .z-menu-inner-m {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: repeat-x; BACKGROUND-POSITION: 0px -82px
}
.z-menu-item-body-over .z-menu-item-inner-m {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/menu-btn.png'))}); BACKGROUND-REPEAT: repeat-x; BACKGROUND-POSITION: 0px -82px
}
.z-menu-body-over .z-menu-inner-m .z-menu-btn {
	COLOR: #233d6d
}
.z-menu-body-seld .z-menu-inner-m .z-menu-btn {
	COLOR: #233d6d
}
.z-menu-item-body-over .z-menu-item-inner-m .z-menu-btn {
	COLOR: #233d6d
}
.z-menubar-hor .z-menu-item-disd * {
	COLOR: gray !important; CURSOR: default !important
}
.z-menubar-ver .z-menu-item-disd * {
	COLOR: gray !important; CURSOR: default !important
}
.z-menubar-hor .z-menu-item-disd .z-menu-item-btn {
	FILTER: alpha(opacity=50); opacity: .5; -moz-opacity: .5
}
.z-menubar-ver .z-menu-item-disd .z-menu-item-btn {
	FILTER: alpha(opacity=50); opacity: .5; -moz-opacity: .5
}
.z-menu-popup-cnt .z-menu-disd .z-menu-img {
	FILTER: alpha(opacity=50); opacity: .5; -moz-opacity: .5
}
.z-menu-popup-cnt .z-menu-item-disd .z-menu-item-img {
	FILTER: alpha(opacity=50); opacity: .5; -moz-opacity: .5
}
.z-menu-popup-shadow {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5)
}
.z-menu-palette-pp {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5)
}
.z-menu-picker-pp {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5)
}
.z-menu-cnt-pp {
	-moz-box-shadow: 1px 1px 3px rgba(0,0,0,0.5); -webkit-box-shadow: 1px 1px 3px rgba(0,0,0,0.5)
}
.z-menu-cnt-body {
	BACKGROUND-COLOR: #fff
}
.z-menu-popup {
	Z-INDEX: 88000; BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/pp-bg.png'))}) #cce6fb repeat-y 0px 0px; BORDER-TOP: #86a4be 1px solid; TOP: 0px; BORDER-RIGHT: #86a4be 1px solid; PADDING-TOP: 2px; LEFT: 0px
}
.z-menu-popup {
	FONT-VARIANT: normal; FONT-STYLE: normal; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
.z-menu-popup A {
	TEXT-DECORATION: none !important
}
.z-menu-popup .z-menu-popup-cnt {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: 0px 0px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px
}
.z-menu-popup-cnt .z-menu {
	LIST-STYLE: none none outside; PADDING-BOTTOM: 1px; LINE-HEIGHT: 100%; MARGIN: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; DISPLAY: block; CURSOR: pointer; PADDING-TOP: 1px
}
.z-menu-popup-cnt .z-menu-item {
	LIST-STYLE: none none outside; PADDING-BOTTOM: 1px; LINE-HEIGHT: 100%; MARGIN: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; DISPLAY: block; CURSOR: pointer; PADDING-TOP: 1px
}
.z-menu-popup-cnt .z-menu-separator {
	LIST-STYLE: none none outside; PADDING-BOTTOM: 1px; LINE-HEIGHT: 100%; MARGIN: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; DISPLAY: block; CURSOR: pointer; PADDING-TOP: 1px
}
.z-menu-popup-cnt .z-menu A.z-menu-cnt {
	PADDING-BOTTOM: 3px; LINE-HEIGHT: 16px; OUTLINE-STYLE: none; PADDING-LEFT: 3px; OUTLINE-WIDTH: 0px; PADDING-RIGHT: 21px; DISPLAY: block; WHITE-SPACE: nowrap; COLOR: #222; PADDING-TOP: 3px
}
.z-menu-popup-cnt .z-menu-item A.z-menu-item-cnt {
	PADDING-BOTTOM: 3px; LINE-HEIGHT: 16px; OUTLINE-STYLE: none; PADDING-LEFT: 3px; OUTLINE-WIDTH: 0px; PADDING-RIGHT: 21px; DISPLAY: block; WHITE-SPACE: nowrap; COLOR: #222; PADDING-TOP: 3px
}
.z-menu-popup-cnt .z-menu .z-menu-img {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MIN-HEIGHT: 16px; PADDING-LEFT: 0px; WIDTH: 16px; PADDING-RIGHT: 0px; DISPLAY: inline-block; BACKGROUND-POSITION: center center; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 100%; VERTICAL-ALIGN: top; BORDER-LEFT-WIDTH: 0px; MARGIN-RIGHT: 9px; PADDING-TOP: 0px
}
.z-menu-popup-cnt .z-menu-item .z-menu-item-img {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MIN-HEIGHT: 16px; PADDING-LEFT: 0px; WIDTH: 16px; PADDING-RIGHT: 0px; DISPLAY: inline-block; BACKGROUND-POSITION: center center; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 100%; VERTICAL-ALIGN: top; BORDER-LEFT-WIDTH: 0px; MARGIN-RIGHT: 9px; PADDING-TOP: 0px
}
.z-menu-popup-cnt .z-menu .z-menu-cnt-img {
}
.z-menu-popup-cnt .z-menu-item-cnt-ck .z-menu-item-img {
}
.z-menu-popup-cnt .z-menu-item-cnt-unck .z-menu-item-img {
}
.z-menu-popup-cnt .z-menu-item-disd {
	COLOR: gray !important; CURSOR: default !important
}
.z-menu-popup-cnt .z-menu-item-disd * {
	COLOR: gray !important; CURSOR: default !important
}
.z-menu-popup-cnt .z-menu-over {
	BORDER-BOTTOM: #a8d8eb 1px solid; BORDER-LEFT: #a8d8eb 1px solid; PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/item-over.png'))}) #ddeefb repeat-x 0px 0px; BORDER-TOP: #a8d8eb 1px solid; BORDER-RIGHT: #a8d8eb 1px solid; PADDING-TOP: 0px
}
.z-menu-popup-cnt .z-menu-item-over {
	BORDER-BOTTOM: #a8d8eb 1px solid; BORDER-LEFT: #a8d8eb 1px solid; PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/menu/item-over.png'))}) #ddeefb repeat-x 0px 0px; BORDER-TOP: #a8d8eb 1px solid; BORDER-RIGHT: #a8d8eb 1px solid; PADDING-TOP: 0px
}
.z-menu-popup-cnt .z-menu-over A.z-menu-cnt {
	COLOR: #233d6d
}
.z-menu-popup-cnt .z-menu-item-over A.z-menu-item-cnt {
	COLOR: #233d6d
}
.z-menu-popup-cnt .z-menu-separator {
	LINE-HEIGHT: 1px; FONT-SIZE: 1px
}
.z-menubar-hor .z-menu-separator {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/img/dot.gif'))}); BACKGROUND-REPEAT: repeat-y; BACKGROUND-POSITION: center top
}
.z-menubar-ver .z-menu-separator {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/img/dot.gif'))}); BACKGROUND-REPEAT: repeat-x; BACKGROUND-POSITION: left center
}
.z-menu-popup-cnt .z-menu-separator-inner {
	BORDER-BOTTOM: #fff 1px solid; LINE-HEIGHT: 1px; BACKGROUND-COLOR: #e0e0e0; MARGIN: 2px 3px; WIDTH: auto; DISPLAY: block; FONT-SIZE: 1px; OVERFLOW: hidden
}
.z-menubar-hor-scroll {
	OVERFLOW: hidden
}
.z-menubar-hor-body {
	MARGIN: 0px; WIDTH: 100%
}
.z-menubar-hor-body-scroll {
	POSITION: relative; MARGIN-LEFT: 20px; OVERFLOW: hidden; MARGIN-RIGHT: 20px
}
.z-menubar-hor-cnt {
	WIDTH: 5000px
}
.z-menubar-hor-left {
	POSITION: absolute; WIDTH: 18px
}
.z-menubar-hor-right {
	POSITION: absolute; WIDTH: 18px
}
.z-menubar-hor-left-scroll {
	Z-INDEX: 25; BORDER-BOTTOM: #8db2e3 1px solid; POSITION: absolute; WIDTH: 18px; HEIGHT: 64px; TOP: 0px; CURSOR: pointer
}
.z-menubar-hor-right-scroll {
	Z-INDEX: 25; BORDER-BOTTOM: #8db2e3 1px solid; POSITION: absolute; WIDTH: 18px; HEIGHT: 64px; TOP: 0px; CURSOR: pointer
}
.z-menubar-hor-left-scroll {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/scroll-l.png'))}) no-repeat 0px -1px; LEFT: 0px
}
.z-menubar-hor-left-scroll-over {
	BACKGROUND-POSITION: -18px 0px
}
.z-menubar-hor-right-scroll {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/scroll-r.png'))}) no-repeat -18px -1px; RIGHT: 0px
}
.z-menubar-hor-right-scroll-over {
	BACKGROUND-POSITION: 0px 0px
}
A.z-menu-item-cnt:visited {
	COLOR: black
}
A.z-menu-item-cnt {
	COLOR: black
}
.z-menu-inner-m .z-menu-btn {
	PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
.z-menu-item-inner-m .z-menu-item-btn {
	PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
.z-paging-os {
	PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; ZOOM: 1; BACKGROUND: white; PADDING-TOP: 5px
}
.z-paging-os .z-paging-os-cnt {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))}); BORDER-BOTTOM: #dae7f6 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; BACKGROUND-COLOR: #c7e5f1; PADDING-LEFT: 3px; PADDING-RIGHT: 3px; BACKGROUND-REPEAT: repeat-x; COLOR: #1725a0; FONT-SIZE: 11px; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #dae7f6 1px solid; TEXT-DECORATION: none; PADDING-TOP: 2px
}
.z-paging-os .z-paging-os-cnt:hover {
	COLOR: red
}
.z-paging-os .z-paging-os-seld {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/paging-os-seld.gif'))}); COLOR: white; FONT-SIZE: 11px; FONT-WEIGHT: bold
}
.z-paging-os .z-paging-os-seld:hover {
	COLOR: #403e39
}
.z-paging-os SPAN {
	COLOR: #555; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
DIV.z-paging-os-cnt-l {
	ZOOM: 1; DISPLAY: inline
}
DIV.z-paging-os-cnt-r {
	ZOOM: 1; DISPLAY: inline
}
DIV.z-paging-os-cnt-m {
	ZOOM: 1; DISPLAY: inline
}
DIV.z-paging-os-cnt-seld-l {
	ZOOM: 1; DISPLAY: inline
}
DIV.z-paging-os-cnt-seld-r {
	ZOOM: 1; DISPLAY: inline
}
DIV.z-paging-os-cnt-seld-m {
	ZOOM: 1; DISPLAY: inline
}
DIV.z-paging-os-cnt-l-over {
	ZOOM: 1; DISPLAY: inline
}
.z-paging-os A.z-paging-os-cnt {
	MARGIN-LEFT: 3px; MARGIN-RIGHT: 3px
}
.z-paging TD {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-paging SPAN {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-paging INPUT {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-paging DIV {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-paging BUTTON {
	FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-paging {
	BORDER-BOTTOM: #b1cbd5 1px solid; POSITION: relative; BORDER-LEFT: #b1cbd5 0px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/bar-bg.png'))}) #daf3ff repeat-x 0px center; BORDER-TOP: #b1cbd5 0px solid; BORDER-RIGHT: #b1cbd5 0px solid; PADDING-TOP: 2px
}
.z-paging TD {
	VERTICAL-ALIGN: middle
}
.z-paging .z-paging-btn {
	WIDTH: auto; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; FONT-SIZE: 11px; CURSOR: pointer; FONT-WEIGHT: normal
}
.z-paging .z-paging-btn {
	TEXT-ALIGN: center; WHITE-SPACE: nowrap; BACKGROUND: 0px 0px; VERTICAL-ALIGN: middle; CURSOR: pointer
}
.z-paging-btn .z-paging-first {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; WIDTH: 21px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; WHITE-SPACE: nowrap; HEIGHT: 21px; CURSOR: pointer; PADDING-TOP: 0px
}
.z-paging-btn .z-paging-last {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; WIDTH: 21px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; WHITE-SPACE: nowrap; HEIGHT: 21px; CURSOR: pointer; PADDING-TOP: 0px
}
.z-paging-btn .z-paging-next {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; WIDTH: 21px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; WHITE-SPACE: nowrap; HEIGHT: 21px; CURSOR: pointer; PADDING-TOP: 0px
}
.z-paging-btn .z-paging-prev {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; WIDTH: 21px; PADDING-RIGHT: 0px; BACKGROUND-REPEAT: no-repeat; WHITE-SPACE: nowrap; HEIGHT: 21px; CURSOR: pointer; PADDING-TOP: 0px
}
.z-paging-btn BUTTON {
	BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; OUTLINE-STYLE: none; MIN-HEIGHT: 13px; PADDING-LEFT: 0px; OUTLINE-WIDTH: 0px; WIDTH: auto; PADDING-RIGHT: 0px; BACKGROUND: 0px 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: visible; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
}
.z-paging .z-paging-sep {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; MARGIN: 0px 2px; WIDTH: 4px; DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/paging/pg-split.gif'))}) no-repeat center center; HEIGHT: 16px; FONT-SIZE: 1px; OVERFLOW: hidden; BORDER-TOP: 0px; CURSOR: default; BORDER-RIGHT: 0px
}
.z-paging-inp {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid
}
.z-paging-btn .z-paging-next {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/paging/pg-btn.png'))}); BACKGROUND-POSITION: 0px 0px
}
.z-paging-btn .z-paging-prev {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/paging/pg-btn.png'))}); BACKGROUND-POSITION: 0px -21px
}
.z-paging-btn .z-paging-last {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/paging/pg-btn.png'))}); BACKGROUND-POSITION: 0px -42px
}
.z-paging-btn .z-paging-first {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/paging/pg-btn.png'))}); BACKGROUND-POSITION: 0px -63px
}
.z-paging-btn-over .z-paging-next {
	BACKGROUND-POSITION: -21px 0px
}
.z-paging-btn-over .z-paging-prev {
	BACKGROUND-POSITION: -21px -21px
}
.z-paging-btn-over .z-paging-last {
	BACKGROUND-POSITION: -21px -42px
}
.z-paging-btn-over .z-paging-first {
	BACKGROUND-POSITION: -21px -63px
}
.z-paging-btn-clk .z-paging-next {
	BACKGROUND-POSITION: -42px 0px
}
.z-paging-btn-clk .z-paging-prev {
	BACKGROUND-POSITION: -42px -21px
}
.z-paging-btn-clk .z-paging-last {
	BACKGROUND-POSITION: -42px -42px
}
.z-paging-btn-clk .z-paging-first {
	BACKGROUND-POSITION: -42px -63px
}
.z-paging .z-paging-btn-disd {
	FILTER: alpha(opacity=40); COLOR: gray; CURSOR: default; opacity: .4; -moz-opacity: .4
}
.z-paging .z-paging-btn-disd * {
	COLOR: gray !important; CURSOR: default !important
}
.z-paging-info {
	POSITION: absolute; COLOR: #444; TOP: 5px; RIGHT: 8px
}
.z-panel-shadow {
	-moz-box-shadow: 0 0 3px rgba(0,0,0,0.5); -webkit-box-shadow: 0 0 3px rgba(0,0,0,0.5); box-shadow: 0 0 3px rgba(0,0,0,0.5); border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-panel-resize-faker {
	Z-INDEX: 60000; BORDER-BOTTOM: #1854c2 1px dashed; POSITION: absolute; FILTER: alpha(opacity=50); BORDER-LEFT: #1854c2 1px dashed; BACKGROUND-COLOR: #d7e6f7; OVERFLOW: hidden; BORDER-TOP: #1854c2 1px dashed; TOP: 0px; BORDER-RIGHT: #1854c2 1px dashed; LEFT: 0px; opacity: .5
}
.z-panel-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-panel-tr {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-panel-bl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-panel-br {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-panel-bl {
	BACKGROUND-POSITION: 0px -5px
}
.z-panel-br {
	POSITION: relative; BACKGROUND-POSITION: right -10px; MARGIN-RIGHT: -5px
}
.z-panel-tr {
	POSITION: relative; BACKGROUND-POSITION: right -10px; MARGIN-RIGHT: -5px
}
.z-panel-br {
	BACKGROUND-POSITION: right bottom
}
.z-panel-hm .z-panel-header {
	PADDING-BOTTOM: 4px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; COLOR: #0f3b82; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: bold; PADDING-TOP: 0px
}
.z-panel-header {
	PADDING-BOTTOM: 4px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; COLOR: #0f3b82; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: bold; PADDING-TOP: 0px
}
.z-panel-header {
	BORDER-BOTTOM: #b1cbd5 1px solid; BORDER-LEFT: #b1cbd5 1px solid; PADDING-BOTTOM: 4px; PADDING-LEFT: 5px; PADDING-RIGHT: 3px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-hm.png'))}) repeat-x 0px 0px; BORDER-TOP: #b1cbd5 1px solid; BORDER-RIGHT: #b1cbd5 1px solid; PADDING-TOP: 5px
}
.z-panel-header-move {
	CURSOR: move
}
.z-panel-hl {
	BORDER-BOTTOM: #a7dcf9 1px solid; PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-hl.png'))}) no-repeat 0px 0px
}
.z-panel-hr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-hr.png'))}) no-repeat right 0px
}
.z-panel-hm {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-hm.png'))}) repeat-x 0px 0px; OVERFLOW: hidden
}
.z-panel-cm {
	BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; BACKGROUND: #cdf0ff; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 6px
}
.z-panel-cl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-clr.png'))}) repeat-y 0px 0px
}
.z-panel-fl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-clr.png'))}) repeat-y 0px 0px
}
.z-panel-cr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-clr.png'))}) repeat-y right 0px
}
.z-panel-fr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/panel-clr.png'))}) repeat-y right 0px
}
.z-panel-fm {
	BACKGROUND: #cdf0ff; OVERFLOW: hidden
}
.z-panel-move-ghost {
	POSITION: absolute; FILTER: alpha(opacity=60) !important; BACKGROUND: #d7e6f7; OVERFLOW: hidden; CURSOR: move !important; opacity: .6
}
.z-panel-move-block {
	BORDER-BOTTOM: #b1cbd5 2px dashed; BORDER-LEFT: #b1cbd5 2px dashed; BORDER-TOP: #b1cbd5 2px dashed; BORDER-RIGHT: #b1cbd5 2px dashed
}
.z-panel-move-ghost DL {
	BORDER-BOTTOM: #b1cbd5 1px solid; BORDER-LEFT: #b1cbd5 1px solid; PADDING-BOTTOM: 0px; LINE-HEIGHT: 0; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; BACKGROUND: #d7e6f7; FONT-SIZE: 0px; OVERFLOW: hidden; BORDER-TOP: #b1cbd5 1px solid; BORDER-RIGHT: #b1cbd5 1px solid; PADDING-TOP: 0px
}
.z-panel {
	OVERFLOW: hidden
}
.z-panel-header {
	OVERFLOW: hidden
}
.z-panel-hl .z-panel-header {
	PADDING-BOTTOM: 4px; BORDER-RIGHT-WIDTH: 0px; BACKGROUND-COLOR: transparent; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; COLOR: #0f3b82; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-panel-body {
	ZOOM: 1; OVERFLOW: hidden
}
.z-panel-children {
	BORDER-BOTTOM: #b1cbd5 1px solid; POSITION: relative; BORDER-LEFT: #b1cbd5 1px solid; BACKGROUND-COLOR: white; ZOOM: 1; OVERFLOW: hidden; BORDER-TOP: #b1cbd5 0px solid; BORDER-RIGHT: #b1cbd5 1px solid
}
.z-panel-body .z-panel-top .z-toolbar {
	BORDER-BOTTOM: #b1cbd5 1px solid; BORDER-LEFT: #b1cbd5 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; OVERFLOW: hidden; BORDER-TOP: #b1cbd5 0px solid; BORDER-RIGHT: #b1cbd5 1px solid; PADDING-TOP: 2px
}
.z-panel-body .z-panel-btm .z-toolbar {
	BORDER-BOTTOM: #b1cbd5 1px solid; BORDER-LEFT: #b1cbd5 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; OVERFLOW: hidden; BORDER-TOP: #b1cbd5 0px solid; BORDER-RIGHT: #b1cbd5 1px solid; PADDING-TOP: 2px
}
.z-panel-cl .z-panel-top .z-toolbar {
	BORDER-BOTTOM-WIDTH: 0px; BORDER-TOP: #b1cbd5 1px solid
}
.z-panel-cl .z-panel-children {
	BORDER-BOTTOM: #b1cbd5 1px solid; BORDER-LEFT: #b1cbd5 1px solid; BACKGROUND-COLOR: transparent; BORDER-TOP: #b1cbd5 1px solid; BORDER-RIGHT: #b1cbd5 1px solid
}
.z-panel-children-noheader {
	BORDER-TOP: #b1cbd5 1px solid
}
.z-panel-cl .z-panel-children {
	BORDER-TOP: #b1cbd5 1px solid
}
.z-panel-noborder .z-panel-children-noborder {
	BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-panel-nobtm2.z-panel-fl {
	DISPLAY: none
}
.z-panel-noheader.z-panel-cm {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
.z-panel-noheader {
	BORDER-BOTTOM-WIDTH: 0px
}
.z-panel-top-noborder .z-toolbar {
	BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-TOP: #b1cbd5 1px solid
}
.z-panel-noborder .z-panel-top-noborder.z-panel-top .z-toolbar {
	BORDER-BOTTOM: #b1cbd5 1px solid; BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-panel-noborder .z-panel-btm-noborder.z-panel-btm .z-toolbar {
	BORDER-RIGHT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-TOP: #b1cbd5 1px solid; BORDER-LEFT-WIDTH: 0px
}
.z-panel-noborder .z-panel-header-noborder.z-panel-header {
	BORDER-BOTTOM: #b1cbd5 1px solid; BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-panel-noheader.z-panel-top .z-toolbar {
	BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-TOP: #b1cbd5 1px solid
}
.z-panel-icon {
	WIDTH: 16px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/btn.gif'))}) no-repeat 0px 0px; FLOAT: right; HEIGHT: 16px; MARGIN-LEFT: 2px; OVERFLOW: hidden; CURSOR: pointer
}
.z-panel-min {
	BACKGROUND-POSITION: 0px 0px
}
.z-panel-min-over {
	BACKGROUND-POSITION: -16px 0px
}
.z-panel-max {
	BACKGROUND-POSITION: 0px -16px
}
.z-panel-max-over {
	BACKGROUND-POSITION: -16px -16px
}
.z-panel-maxd {
	BACKGROUND-POSITION: 0px -32px
}
.z-panel-maxd-over {
	BACKGROUND-POSITION: -16px -32px
}
.z-panel-close {
	BACKGROUND-POSITION: 0px -48px
}
.z-panel-close-over {
	BACKGROUND-POSITION: -16px -48px
}
.z-panel-exp {
	BACKGROUND-POSITION: 0px -64px
}
.z-panel-exp-over {
	BACKGROUND-POSITION: -16px -64px
}
.z-panel-colpsd .z-panel-exp {
	BACKGROUND-POSITION: 0px -96px
}
.z-panel-colpsd {
	HEIGHT: auto !important
}
.z-panel-colpsd .z-panel-exp-over {
	BACKGROUND-POSITION: -16px -96px
}
.z-panel-tm {
	OVERFLOW: visible
}
.z-panel-header {
	ZOOM: 1
}
.z-panel-btm {
	POSITION: relative
}
.z-popup {
	BORDER-BOTTOM: 0px; POSITION: absolute; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: 0px; TOP: 0px; FONT-WEIGHT: normal; BORDER-RIGHT: 0px; PADDING-TOP: 0px; LEFT: 0px
}
.z-popup-plain {
	BORDER-BOTTOM: 0px; POSITION: absolute; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: 0px; TOP: 0px; FONT-WEIGHT: normal; BORDER-RIGHT: 0px; PADDING-TOP: 0px; LEFT: 0px
}
.z-popup .z-popup-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/popup/pp-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-popup .z-popup-tr {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/popup/pp-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-popup .z-popup-bl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/popup/pp-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-popup .z-popup-br {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/popup/pp-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-popup .z-popup-tr {
	POSITION: relative; BACKGROUND-POSITION: right -10px; MARGIN-RIGHT: -5px
}
.z-popup .z-popup-cm {
	PADDING-BOTTOM: 4px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/popup/pp-cm.png'))}) #edf6fc repeat-x 0px 0px; OVERFLOW: hidden; PADDING-TOP: 4px
}
.z-popup .z-popup-cl {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/popup/pp-clr.png'))}) repeat-y 0px 0px; OVERFLOW: hidden
}
.z-popup .z-popup-cr {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/popup/pp-clr.png'))}) repeat-y 0px 0px; OVERFLOW: hidden
}
.z-popup .z-popup-cl {
	PADDING-LEFT: 6px
}
.z-popup .z-popup-cr {
	PADDING-RIGHT: 6px; BACKGROUND-POSITION: right 0px
}
.z-popup .z-popup-bl {
	BACKGROUND-POSITION: 0px -5px
}
.z-popup .z-popup-br {
	POSITION: relative; BACKGROUND-POSITION: right -15px; MARGIN-RIGHT: -5px
}
.z-popup .z-popup-cnt {
	PADDING-BOTTOM: 0px; LINE-HEIGHT: 14px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; ZOOM: 1; COLOR: #444; PADDING-TOP: 0px
}
.z-popup-plain .z-popup-plain-tl {
	DISPLAY: none
}
.z-popup-plain .z-popup-plain-tr {
	DISPLAY: none
}
.z-popup-plain .z-popup-plain-bl {
	DISPLAY: none
}
.z-popup-plain .z-popup-plain-br {
	DISPLAY: none
}
DIV.z-progressmeter {
	BORDER-BOTTOM: #6f9cdb 1px solid; TEXT-ALIGN: left; BORDER-LEFT: #6f9cdb 1px solid; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/prgmeter_bg.png'))}) #e0e8f3 repeat-x 0px 0px; HEIGHT: 17px; OVERFLOW: hidden; BORDER-TOP: #6f9cdb 1px solid; BORDER-RIGHT: #6f9cdb 1px solid
}
SPAN.z-progressmeter-img {
	LINE-HEIGHT: 0; DISPLAY: inline-block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/misc/prgmeter.png'))}) #a4c6f2 repeat-x left center; HEIGHT: 17px; FONT-SIZE: 0px
}
.z-separator-hor {
	LINE-HEIGHT: 0; HEIGHT: 7px; FONT-SIZE: 0px; OVERFLOW: hidden
}
.z-separator-hor-bar {
	LINE-HEIGHT: 0; HEIGHT: 7px; FONT-SIZE: 0px; OVERFLOW: hidden
}
.z-separator-ver {
	WIDTH: 10px; DISPLAY: inline-block; OVERFLOW: hidden
}
.z-separator-ver-bar {
	WIDTH: 10px; DISPLAY: inline-block; OVERFLOW: hidden
}
.z-separator-hor-bar {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/img/dot.gif'))}); BACKGROUND-REPEAT: repeat-x; BACKGROUND-POSITION: left center
}
.z-separator-ver-bar {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/img/dot.gif'))}); BACKGROUND-REPEAT: repeat-y; BACKGROUND-POSITION: center top
}
.z-slider-sphere-hor {
	LINE-HEIGHT: 0; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg.png'))}) no-repeat 0px top; HEIGHT: 22px; FONT-SIZE: 0px; MARGIN-RIGHT: 7px
}
.z-slider-scale {
	LINE-HEIGHT: 0; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg.png'))}) no-repeat 0px top; HEIGHT: 22px; FONT-SIZE: 0px; MARGIN-RIGHT: 7px
}
.z-slider-hor {
	LINE-HEIGHT: 0; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg.png'))}) no-repeat 0px top; HEIGHT: 22px; FONT-SIZE: 0px; MARGIN-RIGHT: 7px
}
.z-slider-sphere-hor-center {
	LINE-HEIGHT: 0; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg.png'))}) no-repeat 0px top; HEIGHT: 22px; FONT-SIZE: 0px; MARGIN-RIGHT: 7px
}
.z-slider-scale-center {
	LINE-HEIGHT: 0; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg.png'))}) no-repeat 0px top; HEIGHT: 22px; FONT-SIZE: 0px; MARGIN-RIGHT: 7px
}
.z-slider-hor-center {
	LINE-HEIGHT: 0; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg.png'))}) no-repeat 0px top; HEIGHT: 22px; FONT-SIZE: 0px; MARGIN-RIGHT: 7px
}
.z-slider-sphere-hor-center {
	POSITION: relative; BACKGROUND-POSITION: right -22px; CURSOR: pointer; MARGIN-RIGHT: -7px
}
.z-slider-scale-center {
	POSITION: relative; BACKGROUND-POSITION: right -22px; CURSOR: pointer; MARGIN-RIGHT: -7px
}
.z-slider-hor-center {
	POSITION: relative; BACKGROUND-POSITION: right -22px; CURSOR: pointer; MARGIN-RIGHT: -7px
}
.z-slider-sphere-hor-btn {
	POSITION: absolute; WIDTH: 14px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-square.png'))}) no-repeat 0px 0px; HEIGHT: 15px; TOP: 3px; CURSOR: pointer; LEFT: 0px
}
.z-slider-scale-btn {
	POSITION: absolute; WIDTH: 14px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-square.png'))}) no-repeat 0px 0px; HEIGHT: 15px; TOP: 3px; CURSOR: pointer; LEFT: 0px
}
.z-slider-hor-btn {
	POSITION: absolute; WIDTH: 14px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-square.png'))}) no-repeat 0px 0px; HEIGHT: 15px; TOP: 3px; CURSOR: pointer; LEFT: 0px
}
.z-slider-scale-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-scale.gif'))})
}
.z-slider-scale-tick {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/ticks.gif'))}); WIDTH: 214px; PADDING-TOP: 6px
}
.z-slider-sphere-ver {
	LINE-HEIGHT: 0; WIDTH: 22px; ZOOM: 1; MARGIN-BOTTOM: 7px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg-ver.png'))}) no-repeat left 0px; FONT-SIZE: 0px
}
.z-slider-ver {
	LINE-HEIGHT: 0; WIDTH: 22px; ZOOM: 1; MARGIN-BOTTOM: 7px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg-ver.png'))}) no-repeat left 0px; FONT-SIZE: 0px
}
.z-slider-sphere-ver-center {
	LINE-HEIGHT: 0; WIDTH: 22px; ZOOM: 1; MARGIN-BOTTOM: 7px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg-ver.png'))}) no-repeat left 0px; FONT-SIZE: 0px
}
.z-slider-ver-center {
	LINE-HEIGHT: 0; WIDTH: 22px; ZOOM: 1; MARGIN-BOTTOM: 7px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-bg-ver.png'))}) no-repeat left 0px; FONT-SIZE: 0px
}
.z-slider-sphere-ver-center {
	POSITION: relative; BACKGROUND-POSITION: -22px bottom; MARGIN-BOTTOM: -7px; CURSOR: pointer
}
.z-slider-ver-center {
	POSITION: relative; BACKGROUND-POSITION: -22px bottom; MARGIN-BOTTOM: -7px; CURSOR: pointer
}
.z-slider-sphere-ver-btn {
	POSITION: absolute; WIDTH: 15px; BOTTOM: 0px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-v-square.png'))}) no-repeat 0px 0px; HEIGHT: 15px; CURSOR: pointer; LEFT: 4px
}
.z-slider-ver-btn {
	POSITION: absolute; WIDTH: 15px; BOTTOM: 0px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-v-square.png'))}) no-repeat 0px 0px; HEIGHT: 15px; CURSOR: pointer; LEFT: 4px
}
.z-slider-sphere-ver-btn {
	HEIGHT: 14px; LEFT: 3px
}
.z-slider-sphere-hor-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-circle.png'))})
}
.z-slider-sphere-ver-btn {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/slider/slider-v-circle.png'))})
}
.z-slider-hor-btn-over {
	BACKGROUND-POSITION: -15px 0px
}
.z-slider-sphere-hor-btn-over {
	BACKGROUND-POSITION: -15px 0px
}
.z-slider-sphere-ver-btn-over {
	BACKGROUND-POSITION: -15px 0px
}
.z-slider-ver-btn-over {
	BACKGROUND-POSITION: -15px 0px
}
.z-slider-scale-btn-over {
	BACKGROUND-POSITION: -15px 0px
}
.z-slider-hor-btn-drag {
	BACKGROUND-POSITION: -30px 0px
}
.z-slider-sphere-hor-btn-drag {
	BACKGROUND-POSITION: -30px 0px
}
.z-slider-sphere-ver-btn-drag {
	BACKGROUND-POSITION: -30px 0px
}
.z-slider-ver-btn-drag {
	BACKGROUND-POSITION: -30px 0px
}
.z-slider-scale-btn-drag {
	BACKGROUND-POSITION: -30px 0px
}
.z-slider-pp {
	FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal
}
.z-tabbox {
	OVERFLOW: hidden
}
.z-tabbox-ver {
	OVERFLOW: hidden
}
.z-tabbox-accordion {
	OVERFLOW: hidden
}
.z-tabbox-accordion-lite {
	OVERFLOW: hidden
}
.z-tabs-cnt .z-tab-hl {
	DISPLAY: block
}
.z-tabs-cnt .z-tab-hr {
	DISPLAY: block
}
.z-tabs-cnt .z-tab-hm {
	DISPLAY: block
}
.z-tabs-cnt .z-tab-text {
	DISPLAY: block
}
.z-tab-text {
	TEXT-OVERFLOW: ellipsis; OVERFLOW: hidden
}
.z-tab-ver-text {
	TEXT-OVERFLOW: ellipsis; OVERFLOW: hidden
}
.z-tab-accordion-text {
	TEXT-OVERFLOW: ellipsis; OVERFLOW: hidden
}
.z-tab-accordion-lite-text {
	TEXT-OVERFLOW: ellipsis; OVERFLOW: hidden
}
.z-tabs .z-tabs-space {
	BORDER-BOTTOM: #7eaac6 1px solid; BORDER-LEFT: #7eaac6 1px solid; LINE-HEIGHT: 0; BACKGROUND: #f2fcff 0px 0px; HEIGHT: 2px; FONT-SIZE: 0px; BORDER-TOP: #7eaac6 0px; BORDER-RIGHT: #7eaac6 1px solid
}
.z-tabs-scroll .z-tabs-space {
	BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: auto; BORDER-LEFT-WIDTH: 0px
}
.z-tabs .z-tabs-cnt {
	LIST-STYLE: none none outside; BORDER-BOTTOM: #7eaac6 1px solid; MARGIN: 0px; PADDING-LEFT: 5px; WIDTH: 100%; ZOOM: 1; DISPLAY: block; BACKGROUND: 0px 0px
}
.z-tabs {
	POSITION: relative; PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: 0px 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-tabs-ver {
	POSITION: relative; PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: 0px 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-toolbar-tabs {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tabs-bg.png'))}); Z-INDEX: 1; POSITION: absolute; PADDING-BOTTOM: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; HEIGHT: 100%; OVERFLOW: hidden; TOP: 0px; RIGHT: 0px; PADDING-TOP: 2px
}
.z-toolbar-tabs-outer {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tabs-bg.png'))}) #c7e3f3 repeat-x 0px top; OVERFLOW: hidden
}
.z-tabs-scroll {
	BORDER-BOTTOM: #7eaac6 1px solid; BORDER-LEFT: #7eaac6 1px solid; PADDING-BOTTOM: 2px; ZOOM: 1; BACKGROUND: #fafdfe 0px 0px; BORDER-TOP: #7eaac6 1px solid; BORDER-RIGHT: #7eaac6 1px solid
}
.z-tabs-header {
	POSITION: relative; MARGIN: 0px; WIDTH: 100%; ZOOM: 1; OVERFLOW: hidden
}
.z-tabs-header .z-clear {
	HEIGHT: 0px
}
.z-tabs-scroll .z-tabs-cnt {
	LIST-STYLE: none none outside; BORDER-BOTTOM: #7eaac6 1px solid; MARGIN: 0px; PADDING-LEFT: 5px; ZOOM: 1; DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tabs-bg.png'))}) #c7e3f3 repeat-x 0px top; PADDING-TOP: 1px; -moz-user-select: none
}
.z-tabs-cnt LI {
	POSITION: relative; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; FONT-FAMILY: Tahoma; FLOAT: left; FONT-SIZE: 11px; CURSOR: default; PADDING-TOP: 0px; -moz-user-select: none
}
.z-tab-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tab-close.gif'))}); Z-INDEX: 15; POSITION: absolute; FILTER: alpha(opacity=80); WIDTH: 11px; ZOOM: 1; DISPLAY: block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 12px; TOP: 3px; CURSOR: pointer; RIGHT: 3px; opacity: .8
}
.z-tab-ver-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tab-close.gif'))}); Z-INDEX: 15; POSITION: absolute; FILTER: alpha(opacity=80); WIDTH: 11px; ZOOM: 1; DISPLAY: block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 12px; TOP: 3px; CURSOR: pointer; RIGHT: 3px; opacity: .8
}
.z-tab-ver-close {
	TOP: 5px; RIGHT: 1px
}
.z-tab-close:hover {
	BACKGROUND-POSITION: -11px 0px
}
.z-tab-close-over {
	BACKGROUND-POSITION: -11px 0px
}
.z-tab-ver-close:hover {
	BACKGROUND-POSITION: -11px 0px
}
.z-tab-ver-close-over {
	BACKGROUND-POSITION: -11px 0px
}
.z-tab-hl {
	POSITION: relative; OUTLINE-STYLE: none; PADDING-LEFT: 10px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tab-corner.png'))}) no-repeat 0px -128px; CURSOR: pointer; -moz-outline: none
}
.z-tab-hr {
	PADDING-RIGHT: 10px; DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tab-corner.png'))}) no-repeat right -128px
}
.z-tab-hm {
	PADDING-LEFT: 2px; PADDING-RIGHT: 1px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tab-hm.png'))}) repeat-x 0px -128px; OVERFLOW: hidden; CURSOR: pointer
}
.z-tab-hm-close {
	PADDING-RIGHT: 10px
}
.z-tabs-edge {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px !important; LINE-HEIGHT: 0 !important; MARGIN: 0px; PADDING-LEFT: 0px !important; WIDTH: 1px; PADDING-RIGHT: 0px !important; ZOOM: 1; BACKGROUND: none transparent scroll repeat 0% 0%; FLOAT: left; FONT-SIZE: 0px !important; OVERFLOW: hidden; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px !important
}
.z-tab .z-tab-hl:hover {
	BACKGROUND-POSITION: 0px -64px
}
.z-tab .z-tab-hl:hover .z-tab-hr {
	BACKGROUND-POSITION: right -64px
}
.z-tab .z-tab-hl:hover .z-tab-hm {
	BACKGROUND-POSITION: right -64px
}
.z-tab .z-tab-hl:hover .z-tab-text {
	COLOR: #0f3b82
}
.z-tab .z-tab-text {
	PADDING-BOTTOM: 4px; FONT-STYLE: normal; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; COLOR: #416aa3; FONT-SIZE: 11px; CURSOR: pointer; PADDING-TOP: 4px; font-size-adjust: none
}
.z-tabs-header-scroll {
	MARGIN-LEFT: 18px; MARGIN-RIGHT: 18px
}
.z-tabs-scroll .z-tabs-right-scroll {
	Z-INDEX: 25; BORDER-BOTTOM: #8db2e3 1px solid; POSITION: absolute; WIDTH: 18px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/scroll-r.png'))}) no-repeat -18px -1px; HEIGHT: 64px; TOP: 0px; CURSOR: pointer; RIGHT: 0px
}
.z-tabs-scroll .z-tabs-left-scroll {
	Z-INDEX: 25; BORDER-BOTTOM: #8db2e3 1px solid; POSITION: absolute; WIDTH: 18px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/scroll-r.png'))}) no-repeat -18px -1px; HEIGHT: 64px; TOP: 0px; CURSOR: pointer; RIGHT: 0px
}
.z-tabs-scroll .z-tabs-left-scroll {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/scroll-l.png'))}) no-repeat 0px -1px; LEFT: 0px
}
.z-tabs-scroll .z-tabs-right-scroll:hover {
	BACKGROUND-POSITION: 0px 0px
}
.z-tabs-scroll .z-tabs-left-scroll:hover {
	BACKGROUND-POSITION: -18px 0px
}
.z-tab-seld .z-tab-hl {
	MARGIN: auto auto -1px; BACKGROUND-POSITION: 0px 0px; CURSOR: default
}
.z-tab-seld .z-tab-hm {
	TEXT-OVERFLOW: ellipsis; BACKGROUND-POSITION: 0px 0px; OVERFLOW: hidden; CURSOR: default
}
.z-tab-seld .z-tab-close {
	FILTER: alpha(opacity=100); opacity: 1
}
.z-tab-seld .z-tab-hr {
	POSITION: relative; BACKGROUND-POSITION: right 0px
}
.z-tab-seld .z-tab-text {
	PADDING-BOTTOM: 5px; FONT-STYLE: normal; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; COLOR: #0f3b82; FONT-SIZE: 11px; CURSOR: default; FONT-WEIGHT: bold; PADDING-TOP: 4px
}
.z-tab-seld .z-tab-hl:hover {
	BACKGROUND-POSITION: 0px 0px
}
.z-tab-seld .z-tab-hl:hover .z-tab-hm {
	BACKGROUND-POSITION: 0px 0px
}
.z-tab-seld .z-tab-hl:hover .z-tab-hr {
	BACKGROUND-POSITION: right 0px
}
.z-tab-disd .z-tab-hl:hover {
	BACKGROUND-POSITION: 0px -128px
}
.z-tab-disd-seld .z-tab-hl:hover {
	BACKGROUND-POSITION: 0px -128px
}
.z-tab-disd .z-tab-hl:hover .z-tab-hm {
	BACKGROUND-POSITION: 0px -128px
}
.z-tab-disd-seld .z-tab-hl:hover .z-tab-hm {
	BACKGROUND-POSITION: 0px -128px
}
.z-tab-disd .z-tab-hl {
	BACKGROUND-POSITION: 0px -128px
}
.z-tab-disd-seld .z-tab-hl {
	BACKGROUND-POSITION: 0px -128px
}
.z-tab-disd .z-tab-hl:hover .z-tab-hr {
	BACKGROUND-POSITION: right -128px; CURSOR: default
}
.z-tab-disd-seld .z-tab-hl:hover .z-tab-hr {
	BACKGROUND-POSITION: right -128px; CURSOR: default
}
.z-tab-disd .z-tab-hr {
	BACKGROUND-POSITION: right -128px; CURSOR: default
}
.z-tab-disd-seld .z-tab-hr {
	BACKGROUND-POSITION: right -128px; CURSOR: default
}
.z-tab-disd .z-tab-hm {
	BACKGROUND-POSITION: right -128px; CURSOR: default
}
.z-tab-disd-seld .z-tab-hm {
	BACKGROUND-POSITION: right -128px; CURSOR: default
}
.z-tab-disd .z-tab-hl:hover .z-tab-text {
	COLOR: gray
}
.z-tab-disd-seld .z-tab-hl:hover .z-tab-text {
	COLOR: gray
}
.z-tab-disd .z-tab-close:hover {
	BACKGROUND-POSITION: 0px 0px
}
.z-tab-disd-seld .z-tab-close:hover {
	BACKGROUND-POSITION: 0px 0px
}
.z-tab-disd .z-tab-hl {
	FILTER: alpha(opacity=40); COLOR: gray; CURSOR: default; opacity: .4
}
.z-tab-disd-seld .z-tab-hl {
	FILTER: alpha(opacity=40); COLOR: gray; CURSOR: default; opacity: .4
}
.z-tab-disd .z-tab-text {
	PADDING-BOTTOM: 4px; FONT-STYLE: normal; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; COLOR: gray; FONT-SIZE: 11px; CURSOR: default; PADDING-TOP: 4px
}
.z-tab-disd-seld .z-tab-text {
	PADDING-BOTTOM: 4px; FONT-STYLE: normal; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; COLOR: gray; FONT-SIZE: 11px; CURSOR: default; PADDING-TOP: 4px
}
.z-tabs-ver {
	FLOAT: left
}
.z-tabs-ver-scroll {
	BORDER-BOTTOM: #7eaac6 1px solid; BORDER-LEFT: #7eaac6 1px solid; BACKGROUND: #deecfd 0px 0px; BORDER-TOP: #7eaac6 1px solid
}
.z-tabs-ver .z-tabs-ver-space {
	BORDER-BOTTOM: #7eaac6 1px solid; BORDER-LEFT: #7eaac6 1px solid; LINE-HEIGHT: 0; WIDTH: 2px; BACKGROUND: #f2fcff 0px 0px; FONT-SIZE: 0px; BORDER-TOP: #7eaac6 1px solid; BORDER-RIGHT: #7eaac6 1px solid
}
.z-tabs-ver-space {
	BORDER-BOTTOM: #7eaac6 1px solid; BORDER-LEFT: #7eaac6 1px solid; LINE-HEIGHT: 0; WIDTH: 2px; BACKGROUND: #f2fcff 0px 0px; FONT-SIZE: 0px; BORDER-TOP: #7eaac6 1px solid; BORDER-RIGHT: #7eaac6 1px solid
}
.z-tabs-ver .z-tabs-ver-space {
	BORDER-TOP: 0px
}
.z-tabs-ver-scroll .z-tabs-ver-space {
	BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: auto; BORDER-LEFT-WIDTH: 0px
}
.z-tabs-ver .z-tabs-ver-header {
	POSITION: relative; ZOOM: 1; BACKGROUND: none transparent scroll repeat 0% 0%; OVERFLOW: hidden
}
.z-tabs-ver-scroll .z-tabs-ver-header {
	POSITION: relative; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tabs-v-bg.png'))}) #c7e3f3 repeat-y 0px 0px; OVERFLOW: hidden
}
.z-tabs-ver-edge {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px !important; LINE-HEIGHT: 0 !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; ZOOM: 1; BACKGROUND: none transparent scroll repeat 0% 0%; HEIGHT: 1px; FONT-SIZE: 0px !important; OVERFLOW: hidden; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px !important
}
.z-tabs-ver .z-tabs-ver-cnt {
	LIST-STYLE: none none outside; MARGIN: 0px; PADDING-LEFT: 1px; ZOOM: 1; DISPLAY: block; HEIGHT: 4096px; BORDER-RIGHT: #7eaac6 1px solid; PADDING-TOP: 2px; -moz-user-select: none
}
.z-tabs-ver .z-tabs-ver-cnt LI {
	POSITION: relative; MARGIN: 0px; DISPLAY: block; FONT-FAMILY: Tahoma; FONT-SIZE: 11px; CURSOR: default; -moz-user-select: none
}
.z-tab-ver-noclose {
	DISPLAY: none
}
.z-tab-ver-hl {
	DISPLAY: block
}
.z-tab-ver-hr {
	DISPLAY: block
}
.z-tab-ver-hm {
	DISPLAY: block
}
.z-tab-ver-text {
	DISPLAY: block
}
.z-tab-ver-hl {
	POSITION: relative; OUTLINE-STYLE: none; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tab-v-corner.png'))}) no-repeat -512px 0px; CURSOR: pointer; PADDING-TOP: 8px; -moz-outline: none
}
.z-tab-ver-hl .z-tab-ver-hr {
	PADDING-BOTTOM: 10px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tab-v-corner.png'))}) no-repeat -512px bottom
}
.z-tab-ver .z-tab-ver-hm {
	PADDING-LEFT: 5px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/tab-v-hm.png'))}) repeat-y -512px 0px; OVERFLOW: hidden; CURSOR: pointer
}
.z-tab-ver .z-tab-ver-hl:hover {
	BACKGROUND-POSITION: -256px 0px
}
.z-tab-ver .z-tab-ver-hl:hover .z-tab-ver-hr {
	BACKGROUND-POSITION: -256px bottom
}
.z-tab-ver .z-tab-ver-hl:hover .z-tab-ver-hm {
	BACKGROUND-POSITION: -256px 0px
}
.z-tab-ver .z-tab-ver-text {
	TEXT-ALIGN: center; FONT-STYLE: normal; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; COLOR: #416aa3; FONT-SIZE: 11px; CURSOR: pointer; font-size-adjust: none
}
.z-tabs-ver-space {
	POSITION: relative; BORDER-LEFT: 0px; FLOAT: left
}
.z-tabbox-ver .z-tabs-ver-header-scroll {
	MARGIN-TOP: 18px; MARGIN-BOTTOM: 18px
}
.z-tabs-ver-up-scroll {
	Z-INDEX: 25; POSITION: absolute; WIDTH: 100%; DISPLAY: block; HEIGHT: 18px; CURSOR: pointer; RIGHT: 0px
}
.z-tabs-ver-down-scroll {
	Z-INDEX: 25; POSITION: absolute; WIDTH: 100%; DISPLAY: block; HEIGHT: 18px; CURSOR: pointer; RIGHT: 0px
}
.z-tabs-ver-up-scroll {
	BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/scroll-u.png'))}) no-repeat 0px 0px; TOP: 0px; BORDER-RIGHT: #7eaac6 1px solid
}
.z-tabs-ver-down-scroll {
	BOTTOM: 0px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/scroll-d.png'))}) no-repeat 0px -18px; BORDER-RIGHT: #8db2e3 1px solid
}
.z-tabs-ver-up-scroll:hover {
	BACKGROUND-POSITION: 1px -18px
}
.z-tabs-ver-down-scroll:hover {
	BACKGROUND-POSITION: 1px 0px
}
.z-tab-ver-seld .z-tab-ver-close {
	FILTER: alpha(opacity=100); opacity: 1
}
.z-tab-ver-seld .z-tab-ver-hl {
	BACKGROUND-POSITION: 0px 0px; CURSOR: default; MARGIN-RIGHT: -2px
}
.z-tab-ver-seld .z-tab-ver-hl .z-tab-ver-hr {
	BACKGROUND-POSITION: 0px bottom
}
.z-tab-ver-seld .z-tab-ver-hl:hover .z-tab-ver-hr {
	BACKGROUND-POSITION: 0px bottom
}
.z-tab-ver-seld .z-tab-ver-hm {
	BACKGROUND-POSITION: 0px 0px; CURSOR: default
}
.z-tab-ver-seld .z-tab-ver-text {
	WHITE-SPACE: nowrap; COLOR: #0f3b82; CURSOR: default; FONT-WEIGHT: bold
}
.z-tab-ver-seld .z-tab-ver-hl:hover {
	BACKGROUND-POSITION: 0px 0px
}
.z-tab-ver-seld .z-tab-ver-hl:hover .z-tab-ver-hm {
	BACKGROUND-POSITION: 0px 0px
}
.z-tab-ver-disd .z-tab-ver-close {
	BACKGROUND-POSITION: 0px 0px
}
.z-tab-ver-disd-seld .z-tab-ver-close:hover {
	BACKGROUND-POSITION: 0px 0px
}
.z-tab-ver-disd .z-tab-ver-hl {
	FILTER: alpha(opacity=60); COLOR: gray; CURSOR: default; opacity: .6
}
.z-tab-ver-disd-seld .z-tab-ver-hl {
	FILTER: alpha(opacity=60); COLOR: gray; CURSOR: default; opacity: .6
}
.z-tab-ver-disd .z-tab-ver-hl {
	BACKGROUND-POSITION: -512px 0px
}
.z-tab-ver-disd-seld .z-tab-ver-hl {
	BACKGROUND-POSITION: -512px 0px
}
.z-tab-ver-disd .z-tab-ver-hl:hover {
	BACKGROUND-POSITION: -512px 0px
}
.z-tab-ver-disd-seld .z-tab-ver-hl:hover {
	BACKGROUND-POSITION: -512px 0px
}
.z-tab-ver-disd .z-tab-ver-hl .z-tab-ver-hr {
	BACKGROUND-POSITION: -512px bottom
}
.z-tab-ver-disd-seld .z-tab-ver-hl .z-tab-ver-hr {
	BACKGROUND-POSITION: -512px bottom
}
.z-tab-ver-disd .z-tab-ver-hl:hover .z-tab-ver-hr {
	BACKGROUND-POSITION: -512px bottom
}
.z-tab-ver-disd-seld .z-tab-ver-hl:hover .z-tab-ver-hr {
	BACKGROUND-POSITION: -512px bottom
}
.z-tab-ver-disd .z-tab-ver-hl .z-tab-ver-hm {
	BACKGROUND-POSITION: -512px 0px; COLOR: gray; CURSOR: default
}
.z-tab-ver-disd-seld .z-tab-ver-hl .z-tab-ver-hm {
	BACKGROUND-POSITION: -512px 0px; COLOR: gray; CURSOR: default
}
.z-tab-ver-disd .z-tab-ver-hl:hover .z-tab-ver-hm {
	BACKGROUND-POSITION: -512px 0px; COLOR: gray; CURSOR: default
}
.z-tab-ver-disd-seld .z-tab-ver-hl:hover .z-tab-ver-hm {
	BACKGROUND-POSITION: -512px 0px; COLOR: gray; CURSOR: default
}
.z-tab-ver-disd .z-tab-ver-text {
	COLOR: gray; CURSOR: default
}
.z-tab-ver-disd-seld .z-tab-ver-text {
	COLOR: gray; CURSOR: default
}
.z-tabpanel {
	BORDER-BOTTOM: #7eaac6 1px solid; BORDER-LEFT: #7eaac6 1px solid; BORDER-TOP: #7eaac6 1px solid; BORDER-RIGHT: #7eaac6 1px solid
}
.z-tabbox-ver .z-tabpanels-ver {
	BORDER-BOTTOM: #7eaac6 1px solid; BORDER-LEFT: #7eaac6 1px solid; BORDER-TOP: #7eaac6 1px solid; BORDER-RIGHT: #7eaac6 1px solid
}
.z-tabbox-ver .z-tabpanels-ver {
	BORDER-LEFT-WIDTH: 0px
}
.z-tabpanel {
	PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; ZOOM: 1; PADDING-TOP: 5px
}
.z-tabbox-ver .z-tabpanel-ver {
	PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; ZOOM: 1; PADDING-TOP: 5px
}
.z-tabpanels-ver {
	POSITION: relative; ZOOM: 1; FLOAT: left; OVERFLOW: hidden
}
.z-tabpanel {
	BORDER-TOP-STYLE: none
}
.z-tabpanel-cnt {
	HEIGHT: 100%
}
.z-tab-accordion-header {
	POSITION: relative; ZOOM: 1; CURSOR: pointer
}
.z-tab-accordion-text {
	PADDING-BOTTOM: 2px; PADDING-RIGHT: 30px; FONT-FAMILY: Tahoma; COLOR: #373737; FONT-SIZE: 11px; CURSOR: pointer; FONT-WEIGHT: bold; TEXT-DECORATION: none
}
.z-tabbox-accordion .z-tabpanel-accordion {
	BORDER-BOTTOM: #abd6ee 1px solid; BORDER-LEFT: #abd6ee 1px solid; PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; ZOOM: 1; BORDER-RIGHT: #abd6ee 1px solid; PADDING-TOP: 5px
}
.z-tab-accordion-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/accd-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-tab-accordion-tr {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/accd-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-tab-accordion-tr {
	POSITION: relative; BACKGROUND-POSITION: right -5px; MARGIN-RIGHT: -5px
}
.z-tab-accordion-hl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/accd-hl.png'))}) no-repeat 0px 0px
}
.z-tab-accordion-hr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/accd-hr.png'))}) no-repeat right 0px
}
.z-tab-accordion-hm {
	PADDING-BOTTOM: 5px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/accd-hm.png'))}) repeat-x 0px 0px; OVERFLOW: hidden; PADDING-TOP: 3px
}
.z-tab-accordion-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/close-off.gif'))}); Z-INDEX: 15; POSITION: absolute; FILTER: alpha(opacity=60); BACKGROUND-COLOR: transparent; WIDTH: 17px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 16px; TOP: 5px; CURSOR: pointer; RIGHT: 10px; opacity: .6
}
.z-tab-accordion-lite-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/close-off.gif'))}); Z-INDEX: 15; POSITION: absolute; FILTER: alpha(opacity=60); BACKGROUND-COLOR: transparent; WIDTH: 17px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 16px; TOP: 5px; CURSOR: pointer; RIGHT: 10px; opacity: .6
}
.z-tab-accordion-lite-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/close-off.gif'))}); TOP: 3px
}
.z-tab-accordion-close-over {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/accd-close-on.gif'))}); FILTER: alpha(opacity=100); opacity: 1.0
}
.z-tab-accordion .z-tab-accordion-close:hover {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/accd-close-on.gif'))}); FILTER: alpha(opacity=100); opacity: 1.0
}
.z-tab-accordion-seld .z-tab-accordion-close {
	FILTER: alpha(opacity=80); opacity: .8
}
.z-tab-accordion-lite-seld .z-tab-accordion-lite-close {
	FILTER: alpha(opacity=80); opacity: .8
}
.z-tab-accordion-disd .z-tab-accordion-header {
	FILTER: alpha(opacity=60); COLOR: gray; CURSOR: default; opacity: .6
}
.z-tab-accordion-disd-seld .z-tab-accordion-header {
	FILTER: alpha(opacity=60); COLOR: gray; CURSOR: default; opacity: .6
}
.z-tab-accordion-disd .z-tab-accordion-close:hover {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/close-off.gif'))}); FILTER: alpha(opacity=60); opacity: .6
}
.z-tab-accordion-disd-seld .z-tab-accordion-close:hover {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/close-off.gif'))}); FILTER: alpha(opacity=60); opacity: .6
}
.z-tab-accordion-lite-disd .z-tab-accordion-lite-close:hover {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/close-off.gif'))}); FILTER: alpha(opacity=60); opacity: .6
}
.z-tab-accordion-lite-disd-seld .z-tab-accordion-lite-close:hover {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/close-off.gif'))}); FILTER: alpha(opacity=60); opacity: .6
}
.z-tab-accordion-disd .z-tab-accordion-text {
	COLOR: gray; CURSOR: default
}
.z-tab-accordion-disd-seld .z-tab-accordion-text {
	COLOR: gray; CURSOR: default
}
.z-tabpanels-accordion-lite {
	BORDER-LEFT: #99bbe8 1px solid; BORDER-TOP: #99bbe8 1px solid; BORDER-RIGHT: #99bbe8 1px solid
}
.z-tab-accordion-lite-header {
	BORDER-BOTTOM: #99bbe8 1px solid; POSITION: relative; BORDER-LEFT: #99bbe8 0px solid; ZOOM: 1; OVERFLOW: hidden; BORDER-TOP: #99bbe8 0px solid; CURSOR: pointer; BORDER-RIGHT: #99bbe8 0px solid
}
.z-tab-accordion-lite-text {
	LINE-HEIGHT: 15px; PADDING-RIGHT: 30px; FONT-FAMILY: Tahoma; COLOR: #373737; FONT-SIZE: 11px; CURSOR: pointer; TEXT-DECORATION: none
}
.z-tabbox-accordion-lite .z-tabpanel-accordion-lite {
	BORDER-BOTTOM: #99bbe8 1px solid; PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; ZOOM: 1; PADDING-TOP: 5px
}
.z-tab-accordion-lite-tl {
	DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/lite-all.png'))}) repeat-x 0px -9px
}
.z-tab-accordion-lite-tr {
	DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/lite-all.png'))}) repeat-x 0px -9px
}
.z-tab-accordion-lite-tm {
	DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/lite-all.png'))}) repeat-x 0px -9px
}
.z-tab-accordion-lite-tl {
	LINE-HEIGHT: 0; PADDING-LEFT: 6px; ZOOM: 1; TEXT-DECORATION: none
}
.z-tab-accordion-lite-tr {
	PADDING-RIGHT: 6px
}
.z-tab-accordion-lite-tm {
	PADDING-BOTTOM: 3px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; OVERFLOW: hidden; PADDING-TOP: 4px
}
.z-tab-accordion-lite-close-over {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/lite-close-on.gif'))}); FILTER: alpha(opacity=100); opacity: 1
}
.z-tab-accordion-lite .z-tab-accordion-lite-close:hover {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tab/lite-close-on.gif'))}); FILTER: alpha(opacity=100); opacity: 1
}
.z-tab-accordion-lite-disd A {
	FILTER: alpha(opacity=60); COLOR: gray; CURSOR: default; opacity: .6
}
.z-tab-accordion-lite-disd-seld A {
	FILTER: alpha(opacity=60); COLOR: gray; CURSOR: default; opacity: .6
}
.z-tab-accordion-lite-disd .z-tab-accordion-lite-text {
	COLOR: gray; CURSOR: default
}
.z-tab-accordion-lite-disd-seld .z-tab-accordion-lite-text {
	COLOR: gray; CURSOR: default
}
.z-toolbar {
	BORDER-BOTTOM: #b1cbd5 1px solid; POSITION: relative; BORDER-LEFT: #b1cbd5 0px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; ZOOM: 1; DISPLAY: block; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/bar-bg.png'))}) #daf3ff repeat-x 0px center; BORDER-TOP: #b1cbd5 0px solid; BORDER-RIGHT: #b1cbd5 0px solid; PADDING-TOP: 2px
}
.z-caption .z-toolbar {
	BORDER-RIGHT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
.z-toolbar-tabs-body {
	FONT-SIZE: 11px
}
.z-toolbar-tabs-body SPAN {
	FONT-SIZE: 11px
}
.z-toolbar-body {
	FONT-SIZE: 11px
}
.z-toolbar-body SPAN {
	FONT-SIZE: 11px
}
.z-toolbar A {
	BORDER-BOTTOM: #d0def0 1px solid; BORDER-LEFT: #d0def0 1px solid; FONT-FAMILY: Tahoma; BACKGROUND: #d0def0; COLOR: black; FONT-SIZE: 11px; BORDER-TOP: #d0def0 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #d0def0 1px solid; TEXT-DECORATION: none
}
.z-toolbar A:visited {
	BORDER-BOTTOM: #d0def0 1px solid; BORDER-LEFT: #d0def0 1px solid; FONT-FAMILY: Tahoma; BACKGROUND: #d0def0; COLOR: black; FONT-SIZE: 11px; BORDER-TOP: #d0def0 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #d0def0 1px solid; TEXT-DECORATION: none
}
.z-toolbar A:hover {
	BORDER-BOTTOM: #d0def0 1px solid; BORDER-LEFT: #d0def0 1px solid; FONT-FAMILY: Tahoma; BACKGROUND: #d0def0; COLOR: black; FONT-SIZE: 11px; BORDER-TOP: #d0def0 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #d0def0 1px solid; TEXT-DECORATION: none
}
.z-toolbar A:hover {
	BORDER-BOTTOM-COLOR: #aca899; BORDER-TOP-COLOR: #f8fbff; BORDER-RIGHT-COLOR: #aca899; BORDER-LEFT-COLOR: #f8fbff
}
.z-caption .z-toolbar A:hover {
	TEXT-DECORATION: underline
}
.z-toolbar-start {
	FLOAT: left; CLEAR: none
}
.z-toolbar-panel .z-toolbar-panel-start .z-toolbar-panel-cnt {
	FLOAT: left; CLEAR: none
}
.z-toolbar-center {
	TEXT-ALIGN: center; MARGIN: 0px auto
}
.z-toolbar-panel .z-toolbar-panel-center .z-toolbar-panel-cnt {
	TEXT-ALIGN: center; MARGIN: 0px auto
}
.z-toolbar-end {
	FLOAT: right; CLEAR: none
}
.z-toolbar-panel .z-toolbar-panel-end .z-toolbar-panel-cnt {
	FLOAT: right; CLEAR: none
}
.z-toolbar-panel {
	PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; PADDING-TOP: 5px
}
.z-toolbar-panel .z-toolbar-panel-body .z-toolbar-panel-hor {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-toolbar-panel .z-toolbar-panel-body .z-toolbar-panel-ver {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-toolbar-panel .z-toolbar-panel-cnt .z-toolbar-panel-hor {
	PADDING-BOTTOM: 3px; PADDING-LEFT: 3px; PADDING-RIGHT: 3px; PADDING-TOP: 3px
}
.z-toolbar-panel .z-toolbar-panel-cnt .z-toolbar-panel-ver {
	PADDING-BOTTOM: 1px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; PADDING-TOP: 1px
}
.z-toolbar-panel .z-toolbar-panel-center {
	TEXT-ALIGN: center
}
.z-toolbarbutton {
	POSITION: relative; PADDING-BOTTOM: 1px; MARGIN: 0px 2px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; ZOOM: 1; DISPLAY: inline-block; VERTICAL-ALIGN: middle; CURSOR: pointer; PADDING-TOP: 1px
}
.z-toolbarbutton-over {
	BORDER-BOTTOM: #7eaac6 1px solid; PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BORDER-TOP: #7eaac6 1px solid; PADDING-TOP: 0px
}
.z-toolbarbutton-body {
	POSITION: relative; PADDING-BOTTOM: 0px; MARGIN: 0px -1px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; ZOOM: 1; VERTICAL-ALIGN: middle; PADDING-TOP: 0px
}
.z-toolbarbutton-over .z-toolbarbutton-body {
	BORDER-LEFT: #7eaac6 1px solid; PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BORDER-RIGHT: #7eaac6 1px solid; PADDING-TOP: 0px
}
.z-toolbarbutton-cnt {
	POSITION: relative; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; ZOOM: 1; FONT-FAMILY: Tahoma; FONT-SIZE: 11px; FONT-WEIGHT: normal; PADDING-TOP: 2px
}
.z-toolbarbutton {
	DISPLAY: inline
}
.z-toolbarbutton-disd * {
	COLOR: gray !important; CURSOR: default !important
}
.z-toolbarbutton-disd * {
	FILTER: alpha(opacity=50); opacity: .5; -moz-opacity: .5
}
DIV.z-tree {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; ZOOM: 1; BACKGROUND: #dae7f6; OVERFLOW: hidden; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid
}
DIV.z-tree-header {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-tree-header TR {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-tree-footer {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-tree-header {
	OVERFLOW: hidden
}
DIV.z-tree-footer {
	OVERFLOW: hidden
}
DIV.z-tree-header TR.z-treecols {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))})
}
DIV.z-tree-header TR.z-auxhead {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))})
}
DIV.z-tree-header TH.z-treecol {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-tree-header TH.z-auxheader {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-tree-body {
	POSITION: relative; BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-dottree-body {
	POSITION: relative; BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-filetree-body {
	POSITION: relative; BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-vfiletree-body {
	POSITION: relative; BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-tree-pgi-b {
	OVERFLOW: hidden; BORDER-TOP: #aab 1px solid
}
DIV.z-tree-pgi-t {
	BORDER-BOTTOM: #aab 1px solid; OVERFLOW: hidden
}
DIV.z-tree-body TD.z-treecell {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-tree-footer TD.z-treefooter {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-tree-footer {
	BACKGROUND: #dae7f6; BORDER-TOP: #9eb6ce 1px solid
}
TR.z-tree-faker {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-tree-faker TH {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-tree-faker DIV {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-dottree-faker {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-dottree-faker TH {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-dottree-faker DIV {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-filetree-faker {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-filetree-faker TH {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-filetree-faker DIV {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-vfiletree-faker {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-vfiletree-faker TH {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
TR.z-vfiletree-faker DIV {
	PADDING-BOTTOM: 0px !important; BORDER-RIGHT-WIDTH: 0px !important; MARGIN: 0px; PADDING-LEFT: 0px !important; PADDING-RIGHT: 0px !important; BORDER-TOP-WIDTH: 0px !important; BORDER-BOTTOM-WIDTH: 0px !important; HEIGHT: 0px !important; FONT-SIZE: 11px !important; BORDER-LEFT-WIDTH: 0px !important; PADDING-TOP: 0px !important
}
SPAN.z-tree-ico {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-tree-line {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.checkmark-spacer {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-tree-ico {
	BACKGROUND-REPEAT: no-repeat
}
SPAN.z-tree-root-open {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/toggle.gif'))})
}
SPAN.z-tree-tee-open {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/toggle.gif'))})
}
SPAN.z-tree-last-open {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/toggle.gif'))})
}
SPAN.z-tree-root-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/toggle.gif'))})
}
SPAN.z-tree-tee-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/toggle.gif'))})
}
SPAN.z-tree-last-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/toggle.gif'))})
}
SPAN.z-tree-root-open {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-tree-tee-open {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-tree-last-open {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-tree-root-close {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-tree-tee-close {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-tree-last-close {
	BACKGROUND-POSITION: 0px 0px
}
DIV.z-treefooter-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-treecell-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-treecol-cnt {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma; WHITE-SPACE: nowrap; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-SIZE: 11px; BORDER-LEFT-WIDTH: 0px; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-treefooter-cnt {
	OVERFLOW: hidden; CURSOR: default
}
DIV.z-treecol-cnt {
	OVERFLOW: hidden; CURSOR: default
}
.z-word-wrap DIV.z-treecell-cnt {
	WORD-WRAP: break-word
}
.z-word-wrap DIV.z-treefooter-cnt {
	WORD-WRAP: break-word
}
.z-word-wrap DIV.z-treecol-cnt {
	WORD-WRAP: break-word
}
.z-tree-header .z-treecol-sizing.z-treecol {
	CURSOR: e-resize
}
.z-tree-header .z-treecol-sizing.z-treecol .z-treecol-cnt {
	CURSOR: e-resize
}
.z-dottree-header .z-treecol-sizing.z-treecol {
	CURSOR: e-resize
}
.z-dottree-header .z-treecol-sizing.z-treecol .z-treecol-cnt {
	CURSOR: e-resize
}
.z-filetree-header .z-treecol-sizing.z-treecol {
	CURSOR: e-resize
}
.z-filetree-header .z-treecol-sizing.z-treecol .z-treecol-cnt {
	CURSOR: e-resize
}
.z-vfiletree-header .z-treecol-sizing.z-treecol {
	CURSOR: e-resize
}
.z-vfiletree-header .z-treecol-sizing.z-treecol .z-treecol-cnt {
	CURSOR: e-resize
}
DIV.z-tree-header TH.z-treecol-sort DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_hint.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-dottree-header TH.z-treecol-sort DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_hint.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-filetree-header TH.z-treecol-sort DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_hint.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-vfiletree-header TH.z-treecol-sort DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_hint.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-tree-header TH.z-treecol-sort-asc DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_asc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-dottree-header TH.z-treecol-sort-asc DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_asc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-filetree-header TH.z-treecol-sort-asc DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_asc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-vfiletree-header TH.z-treecol-sort-asc DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_asc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-tree-header TH.z-treecol-sort-dsc DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_dsc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-dottree-header TH.z-treecol-sort-dsc DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_dsc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-filetree-header TH.z-treecol-sort-dsc DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_dsc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
DIV.z-vfiletree-header TH.z-treecol-sort-dsc DIV.z-treecol-cnt {
	PADDING-RIGHT: 9px; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/sort/v_dsc.gif'))}) no-repeat 99% center; CURSOR: pointer
}
TR.z-treerow {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
TR.z-treerow A {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
TR.z-treerow A:visited {
	COLOR: black; FONT-SIZE: 11px; FONT-WEIGHT: normal; TEXT-DECORATION: none
}
TR.z-treerow A:hover {
	TEXT-DECORATION: underline
}
TR.z-treerow-disd * {
	COLOR: #c5cacb !important; CURSOR: default !important
}
TD.z-treecell-disd * {
	COLOR: #c5cacb !important; CURSOR: default !important
}
TR.z-treerow-disd A:visited {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
TR.z-treerow-disd A:hover {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
TD.z-treecell-disd A:visited {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
TD.z-treecell-disd A:hover {
	BORDER-BOTTOM-COLOR: #d0def0 !important; BORDER-TOP-COLOR: #d0def0 !important; BORDER-RIGHT-COLOR: #d0def0 !important; BORDER-LEFT-COLOR: #d0def0 !important; CURSOR: default !important; TEXT-DECORATION: none !important
}
TR.z-treerow-seld {
	BORDER-BOTTOM: #6f97d2 1px solid; BORDER-LEFT: #6f97d2 1px solid; BACKGROUND: #bcd2ef; BORDER-TOP: #6f97d2 1px solid; BORDER-RIGHT: #6f97d2 1px solid
}
TR.z-treerow-over {
	BACKGROUND: #dae7f6
}
TR.z-treerow-over-seld {
	BACKGROUND: #6eadff
}
TR.z-treerow TD.z-treerow-focus {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/focusd.png'))}); BACKGROUND-REPEAT: no-repeat
}
DIV.z-dottree {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; ZOOM: 1; BACKGROUND: #dae7f6; OVERFLOW: hidden; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid
}
DIV.z-dottree-header {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-dottree-header TR {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-dottree-footer {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-dottree-header {
	OVERFLOW: hidden
}
DIV.z-dottree-footer {
	OVERFLOW: hidden
}
DIV.z-dottree-header TR.z-treecols {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))})
}
DIV.z-tree-header TR.z-auxhead {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))})
}
DIV.z-dottree-header TH {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-dottree-body {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-dottree-pgi-b {
	OVERFLOW: hidden; BORDER-TOP: #aab 1px solid
}
DIV.z-dottree-pgi-t {
	BORDER-BOTTOM: #aab 1px solid; OVERFLOW: hidden
}
DIV.z-dottree-body TD.z-treecell {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-dottree-footer TD.z-treefooter {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-dottree-footer {
	BACKGROUND: #dae7f6; BORDER-TOP: #9eb6ce 1px solid
}
SPAN.z-dottree-ico {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-dottree-line {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-dottree-root-open {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/dot-toggle.gif'))})
}
SPAN.z-dottree-root-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/dot-toggle.gif'))})
}
SPAN.z-dottree-root-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-dottree-root-close {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-dottree-tee-open {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/tee-open.gif'))})
}
SPAN.z-dottree-tee-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/tee-close.gif'))})
}
SPAN.z-dottree-last-open {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/tee-last-open.gif'))})
}
SPAN.z-dottree-last-close {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/tee-last-close.gif'))})
}
SPAN.z-dottree-tee {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/tee.gif'))})
}
SPAN.z-dottree-vbar {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/tee-vbar.gif'))})
}
SPAN.z-dottree-last {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/tee-last.gif'))})
}
SPAN.z-dottree-spacer {
	BACKGROUND: none transparent scroll repeat 0% 0%
}
SPAN.z-dottree-firstspacer {
	BACKGROUND: none transparent scroll repeat 0% 0%
}
DIV.z-filetree {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; ZOOM: 1; BACKGROUND: #dae7f6; OVERFLOW: hidden; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid
}
DIV.z-filetree-header {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-filetree-header TR {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-filetree-footer {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-filetree-header {
	OVERFLOW: hidden
}
DIV.z-filetree-footer {
	OVERFLOW: hidden
}
DIV.z-filetree-header TR.z-treecols {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))})
}
DIV.z-tree-header TR.z-auxhead {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))})
}
DIV.z-filetree-header TH.z-treecol {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-filetree-header TH.z-auxheader {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-filetree-body {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-filetree-pgi-b {
	OVERFLOW: hidden; BORDER-TOP: #aab 1px solid
}
DIV.z-filetree-pgi-t {
	BORDER-BOTTOM: #aab 1px solid; OVERFLOW: hidden
}
DIV.z-filetree-body TD.z-treecell {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-filetree-footer TD.z-treefooter {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-filetree-footer {
	BACKGROUND: #dae7f6; BORDER-TOP: #9eb6ce 1px solid
}
SPAN.z-filetree-line {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-filetree-ico {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-filetree-firstspacer {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-filetree-ico {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/folder-toggle.gif'))})
}
SPAN.z-filetree-firstspacer {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/folder-toggle.gif'))})
}
SPAN.z-filetree-root-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-filetree-root-close {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-filetree-root-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-filetree-tee-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-filetree-last-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-filetree-root-close {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-filetree-tee-close {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-filetree-last-close {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-filetree-firstspacer {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-filetree-tee {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/entity.gif'))})
}
SPAN.z-filetree-last {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/entity.gif'))})
}
SPAN.z-filetree-vbar {
	BACKGROUND: none transparent scroll repeat 0% 0%
}
SPAN.z-filetree-spacer {
	BACKGROUND: none transparent scroll repeat 0% 0%
}
DIV.z-vfiletree {
	BORDER-BOTTOM: #86a4be 1px solid; BORDER-LEFT: #86a4be 1px solid; ZOOM: 1; BACKGROUND: #dae7f6; OVERFLOW: hidden; BORDER-TOP: #86a4be 1px solid; BORDER-RIGHT: #86a4be 1px solid
}
DIV.z-vfiletree-header {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-vfiletree-header TR {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-vfiletree-footer {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px
}
DIV.z-vfiletree-header {
	OVERFLOW: hidden
}
DIV.z-vfiletree-footer {
	OVERFLOW: hidden
}
DIV.z-vfiletree-header TR.z-treecols {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))})
}
DIV.z-tree-header TR.z-auxhead {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/grid/column-bg.png'))})
}
DIV.z-vfiletree-header TH.z-treecol {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-vfiletree-header TH.z-auxheader {
	BORDER-BOTTOM: #9eb6ce 1px solid; BORDER-LEFT: #dae7f6 1px solid; PADDING-BOTTOM: 2px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; WHITE-SPACE: nowrap; FONT-SIZE: 11px; OVERFLOW: hidden; BORDER-TOP: #dae7f6 1px solid; FONT-WEIGHT: normal; BORDER-RIGHT: #9eb6ce 1px solid; PADDING-TOP: 2px
}
DIV.z-vfiletree-body {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 100%; BACKGROUND: white; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: auto; BORDER-LEFT-WIDTH: 0px
}
DIV.z-vfiletree-pgi-b {
	OVERFLOW: hidden; BORDER-TOP: #aab 1px solid
}
DIV.z-vfiletree-pgi-t {
	BORDER-BOTTOM: #aab 1px solid; OVERFLOW: hidden
}
DIV.z-vfiletree-body TD.z-treecell {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-vfiletree-footer TD.z-treefooter {
	PADDING-BOTTOM: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: pointer; FONT-WEIGHT: normal; PADDING-TOP: 0px
}
DIV.z-vfiletree-footer {
	BACKGROUND: #dae7f6; BORDER-TOP: #9eb6ce 1px solid
}
SPAN.z-vfiletree-line {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-vfiletree-ico {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-vfiletree-firstspacer {
	MIN-HEIGHT: 18px; WIDTH: 18px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; HEIGHT: 100%; VERTICAL-ALIGN: top
}
SPAN.z-vfiletree-ico {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/vfolder-toggle.png'))})
}
SPAN.z-vfiletree-firstspacer {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/vfolder-toggle.png'))})
}
SPAN.z-vfiletree-root-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-vfiletree-root-close {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-vfiletree-root-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-vfiletree-tee-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-vfiletree-last-open {
	BACKGROUND-POSITION: 0px 0px
}
SPAN.z-vfiletree-root-close {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-vfiletree-tee-close {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-vfiletree-last-close {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-vfiletree-firstspacer {
	BACKGROUND-POSITION: 0px -18px
}
SPAN.z-vfiletree-tee {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/ventity.png'))})
}
SPAN.z-vfiletree-last {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/tree/ventity.png'))})
}
SPAN.z-vfiletree-vbar {
	BACKGROUND: none transparent scroll repeat 0% 0%
}
SPAN.z-vfiletree-spacer {
	BACKGROUND: none transparent scroll repeat 0% 0%
}
DIV.z-tree-header {
	POSITION: relative
}
DIV.z-dottree-header {
	POSITION: relative
}
DIV.z-filetree-header {
	POSITION: relative
}
DIV.z-vfiletree-header {
	POSITION: relative
}
DIV.z-tree-footer {
	POSITION: relative
}
DIV.z-dottree-footer {
	POSITION: relative
}
DIV.z-filetree-footer {
	POSITION: relative
}
DIV.z-vfiletree-footer {
	POSITION: relative
}
DIV.z-tree-header TH.z-treecol {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-tree-header TH.z-auxheader {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-dottree-header TH.z-treecol {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-dottree-header TH.z-auxheader {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-filetree-header TH.z-treecol {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-filetree-header TH.z-auxheader {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-vfiletree-header TH.z-treecol {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-vfiletree-header TH.z-auxheader {
	TEXT-OVERFLOW: ellipsis
}
DIV.z-treecol-cnt {
	WHITE-SPACE: nowrap
}
DIV.z-dottreecol-cnt {
	WHITE-SPACE: nowrap
}
DIV.z-filetreecol-cnt {
	WHITE-SPACE: nowrap
}
DIV.z-vfiletreecol-cnt {
	WHITE-SPACE: nowrap
}
.z-auxheader-cnt {
	WHITE-SPACE: nowrap
}
DIV.z-treefooter-cnt {
	POSITION: relative
}
DIV.z-treecell-cnt {
	POSITION: relative
}
DIV.z-treecol-cnt {
	POSITION: relative
}
DIV.z-dottreefooter-cnt {
	POSITION: relative
}
DIV.z-dottreecell-cnt {
	POSITION: relative
}
DIV.z-dottreecol-cnt {
	POSITION: relative
}
DIV.z-filetreefooter-cnt {
	POSITION: relative
}
DIV.z-filetreecell-cnt {
	POSITION: relative
}
DIV.z-filetreecol-cnt {
	POSITION: relative
}
DIV.z-vfiletreefooter-cnt {
	POSITION: relative
}
DIV.z-vfiletreecell-cnt {
	POSITION: relative
}
DIV.z-vfiletreecol-cnt {
	POSITION: relative
}
.z-auxheader-cnt {
	POSITION: relative
}
DIV.z-treefooter-cnt {
	WIDTH: 100%
}
DIV.z-dottreefooter-cnt {
	WIDTH: 100%
}
DIV.z-filetreefooter-cnt {
	WIDTH: 100%
}
DIV.z-vfiletreefooter-cnt {
	WIDTH: 100%
}
DIV.z-treecell-cnt {
	WIDTH: 100%
}
DIV.z-dottreecell-cnt {
	WIDTH: 100%
}
DIV.z-filetreecell-cnt {
	WIDTH: 100%
}
DIV.z-vfiletreecell-cnt {
	WIDTH: 100%
}
DIV.z-tree-body {
	POSITION: relative
}
DIV.z-dottree-body {
	POSITION: relative
}
DIV.z-filetree-body {
	POSITION: relative
}
DIV.z-vfiletree-body {
	POSITION: relative
}
TR.z-tree-faker {
	POSITION: absolute; TOP: -1000px; LEFT: -1000px
}
TR.z-dottree-faker {
	POSITION: absolute; TOP: -1000px; LEFT: -1000px
}
TR.z-filetree-faker {
	POSITION: absolute; TOP: -1000px; LEFT: -1000px
}
TR.z-vfiletree-faker {
	POSITION: absolute; TOP: -1000px; LEFT: -1000px
}
SPAN.z-tree-root-open {
	HEIGHT: 18px
}
SPAN.z-tree-root-close {
	HEIGHT: 18px
}
SPAN.z-tree-tee-open {
	HEIGHT: 18px
}
SPAN.z-tree-tee-close {
	HEIGHT: 18px
}
SPAN.z-tree-last-open {
	HEIGHT: 18px
}
SPAN.z-tree-last-close {
	HEIGHT: 18px
}
SPAN.z-tree-tee {
	HEIGHT: 18px
}
SPAN.z-tree-vbar {
	HEIGHT: 18px
}
SPAN.z-tree-last {
	HEIGHT: 18px
}
SPAN.z-tree-spacer {
	HEIGHT: 18px
}
SPAN.z-dottree-root-open {
	HEIGHT: 18px
}
SPAN.z-dottree-root-close {
	HEIGHT: 18px
}
SPAN.z-dottree-tee-open {
	HEIGHT: 18px
}
SPAN.z-dottree-tee-close {
	HEIGHT: 18px
}
SPAN.z-dottree-last-open {
	HEIGHT: 18px
}
SPAN.z-dottree-last-close {
	HEIGHT: 18px
}
SPAN.z-dottree-tee {
	HEIGHT: 18px
}
SPAN.z-dottree-vbar {
	HEIGHT: 18px
}
SPAN.z-dottree-last {
	HEIGHT: 18px
}
SPAN.z-dottree-spacer {
	HEIGHT: 18px
}
SPAN.z-filetree-root-open {
	HEIGHT: 18px
}
SPAN.z-filetree-root-close {
	HEIGHT: 18px
}
SPAN.z-filetree-tee-open {
	HEIGHT: 18px
}
SPAN.z-filetree-tee-close {
	HEIGHT: 18px
}
SPAN.z-filetree-last-open {
	HEIGHT: 18px
}
SPAN.z-filetree-last-close {
	HEIGHT: 18px
}
SPAN.z-filetree-tee {
	HEIGHT: 18px
}
SPAN.z-filetree-vbar {
	HEIGHT: 18px
}
SPAN.z-filetree-last {
	HEIGHT: 18px
}
SPAN.z-filetree-spacer {
	HEIGHT: 18px
}
SPAN.z-vfiletree-root-open {
	HEIGHT: 18px
}
SPAN.z-vfiletree-root-close {
	HEIGHT: 18px
}
SPAN.z-vfiletree-tee-open {
	HEIGHT: 18px
}
SPAN.z-vfiletree-tee-close {
	HEIGHT: 18px
}
SPAN.z-vfiletree-last-open {
	HEIGHT: 18px
}
SPAN.z-vfiletree-last-close {
	HEIGHT: 18px
}
SPAN.z-vfiletree-tee {
	HEIGHT: 18px
}
SPAN.z-vfiletree-vbar {
	HEIGHT: 18px
}
SPAN.z-vfiletree-last {
	HEIGHT: 18px
}
SPAN.z-vfiletree-spacer {
	HEIGHT: 18px
}
.z-treerow-img {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 2px; MIN-HEIGHT: 13px; PADDING-LEFT: 0px; WIDTH: 13px; PADDING-RIGHT: 0px; DISPLAY: inline-block; BACKGROUND: no-repeat center center; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 13px; VERTICAL-ALIGN: top; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.z-treerow-img-checkbox {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/check-sprite.gif'))}); BACKGROUND-POSITION: 0px 0px
}
.z-treerow-img-radio {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/common/check-sprite.gif'))}); BACKGROUND-POSITION: 0px 0px
}
.z-treerow-img-radio {
	BACKGROUND-POSITION: 0px -13px
}
.z-treerow-over .z-treerow-img-radio {
	BACKGROUND-POSITION: -13px -13px
}
.z-treerow-seld .z-treerow-img-radio {
	BACKGROUND-POSITION: -26px -13px
}
.z-treerow-over-seld .z-treerow-img-radio {
	BACKGROUND-POSITION: -39px -13px
}
.z-treerow-over .z-treerow-img-checkbox {
	BACKGROUND-POSITION: -13px 0px
}
.z-treerow-seld .z-treerow-img-checkbox {
	BACKGROUND-POSITION: -26px 0px
}
.z-treerow-over-seld .z-treerow-img-checkbox {
	BACKGROUND-POSITION: -39px 0px
}
.z-treerow-img-disd {
	FILTER: alpha(opacity=60); opacity: .6; -moz-opacity: .6
}
.z-window-modal-shadow {
	-moz-box-shadow: 0 0 3px rgba(0,0,0,0.5); -webkit-box-shadow: 0 0 3px rgba(0,0,0,0.5); box-shadow: 0 0 3px rgba(0,0,0,0.5); border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-window-overlapped-shadow {
	-moz-box-shadow: 0 0 3px rgba(0,0,0,0.5); -webkit-box-shadow: 0 0 3px rgba(0,0,0,0.5); box-shadow: 0 0 3px rgba(0,0,0,0.5); border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-window-popup-shadow {
	-moz-box-shadow: 0 0 3px rgba(0,0,0,0.5); -webkit-box-shadow: 0 0 3px rgba(0,0,0,0.5); box-shadow: 0 0 3px rgba(0,0,0,0.5); border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-window-embedded-shadow {
	-moz-box-shadow: 0 0 3px rgba(0,0,0,0.5); -webkit-box-shadow: 0 0 3px rgba(0,0,0,0.5); box-shadow: 0 0 3px rgba(0,0,0,0.5); border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-window-highlighted-shadow {
	-moz-box-shadow: 0 0 3px rgba(0,0,0,0.5); -webkit-box-shadow: 0 0 3px rgba(0,0,0,0.5); box-shadow: 0 0 3px rgba(0,0,0,0.5); border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px
}
.z-window-modal-resize-faker {
	Z-INDEX: 60000; BORDER-BOTTOM: #1854c2 1px dashed; POSITION: absolute; FILTER: alpha(opacity=50); BORDER-LEFT: #1854c2 1px dashed; BACKGROUND-COLOR: #d7e6f7; OVERFLOW: hidden; BORDER-TOP: #1854c2 1px dashed; TOP: 0px; BORDER-RIGHT: #1854c2 1px dashed; LEFT: 0px; opacity: .5
}
.z-window-overlapped-resize-faker {
	Z-INDEX: 60000; BORDER-BOTTOM: #1854c2 1px dashed; POSITION: absolute; FILTER: alpha(opacity=50); BORDER-LEFT: #1854c2 1px dashed; BACKGROUND-COLOR: #d7e6f7; OVERFLOW: hidden; BORDER-TOP: #1854c2 1px dashed; TOP: 0px; BORDER-RIGHT: #1854c2 1px dashed; LEFT: 0px; opacity: .5
}
.z-window-popup-resize-faker {
	Z-INDEX: 60000; BORDER-BOTTOM: #1854c2 1px dashed; POSITION: absolute; FILTER: alpha(opacity=50); BORDER-LEFT: #1854c2 1px dashed; BACKGROUND-COLOR: #d7e6f7; OVERFLOW: hidden; BORDER-TOP: #1854c2 1px dashed; TOP: 0px; BORDER-RIGHT: #1854c2 1px dashed; LEFT: 0px; opacity: .5
}
.z-window-embedded-resize-faker {
	Z-INDEX: 60000; BORDER-BOTTOM: #1854c2 1px dashed; POSITION: absolute; FILTER: alpha(opacity=50); BORDER-LEFT: #1854c2 1px dashed; BACKGROUND-COLOR: #d7e6f7; OVERFLOW: hidden; BORDER-TOP: #1854c2 1px dashed; TOP: 0px; BORDER-RIGHT: #1854c2 1px dashed; LEFT: 0px; opacity: .5
}
.z-window-highlighted-resize-faker {
	Z-INDEX: 60000; BORDER-BOTTOM: #1854c2 1px dashed; POSITION: absolute; FILTER: alpha(opacity=50); BORDER-LEFT: #1854c2 1px dashed; BACKGROUND-COLOR: #d7e6f7; OVERFLOW: hidden; BORDER-TOP: #1854c2 1px dashed; TOP: 0px; BORDER-RIGHT: #1854c2 1px dashed; LEFT: 0px; opacity: .5
}
.z-window-resize-faker {
	Z-INDEX: 60000; BORDER-BOTTOM: #1854c2 1px dashed; POSITION: absolute; FILTER: alpha(opacity=50); BORDER-LEFT: #1854c2 1px dashed; BACKGROUND-COLOR: #d7e6f7; OVERFLOW: hidden; BORDER-TOP: #1854c2 1px dashed; TOP: 0px; BORDER-RIGHT: #1854c2 1px dashed; LEFT: 0px; opacity: .5
}
.z-window-modal-move-ghost {
	POSITION: absolute; FILTER: alpha(opacity=65) !important; BACKGROUND: #d7e6f7; OVERFLOW: hidden; CURSOR: move !important; opacity: .65
}
.z-window-overlapped-move-ghost {
	POSITION: absolute; FILTER: alpha(opacity=65) !important; BACKGROUND: #d7e6f7; OVERFLOW: hidden; CURSOR: move !important; opacity: .65
}
.z-window-popup-move-ghost {
	POSITION: absolute; FILTER: alpha(opacity=65) !important; BACKGROUND: #d7e6f7; OVERFLOW: hidden; CURSOR: move !important; opacity: .65
}
.z-window-highlighted-move-ghost {
	POSITION: absolute; FILTER: alpha(opacity=65) !important; BACKGROUND: #d7e6f7; OVERFLOW: hidden; CURSOR: move !important; opacity: .65
}
.z-window-move-ghost {
	POSITION: absolute; FILTER: alpha(opacity=65) !important; BACKGROUND: #d7e6f7; OVERFLOW: hidden; CURSOR: move !important; opacity: .65
}
.z-window-modal-move-ghost DL {
	BORDER-BOTTOM: #538ba2 1px solid; BORDER-LEFT: #538ba2 1px solid; PADDING-BOTTOM: 0px; LINE-HEIGHT: 0; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; BACKGROUND: #d7e6f7; FONT-SIZE: 0px; OVERFLOW: hidden; BORDER-TOP: #538ba2 1px solid; BORDER-RIGHT: #538ba2 1px solid; PADDING-TOP: 0px
}
.z-window-overlapped-move-ghost DL {
	BORDER-BOTTOM: #538ba2 1px solid; BORDER-LEFT: #538ba2 1px solid; PADDING-BOTTOM: 0px; LINE-HEIGHT: 0; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; BACKGROUND: #d7e6f7; FONT-SIZE: 0px; OVERFLOW: hidden; BORDER-TOP: #538ba2 1px solid; BORDER-RIGHT: #538ba2 1px solid; PADDING-TOP: 0px
}
.z-window-popup-move-ghost DL {
	BORDER-BOTTOM: #538ba2 1px solid; BORDER-LEFT: #538ba2 1px solid; PADDING-BOTTOM: 0px; LINE-HEIGHT: 0; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; BACKGROUND: #d7e6f7; FONT-SIZE: 0px; OVERFLOW: hidden; BORDER-TOP: #538ba2 1px solid; BORDER-RIGHT: #538ba2 1px solid; PADDING-TOP: 0px
}
.z-window-highlighted-move-ghost DL {
	BORDER-BOTTOM: #538ba2 1px solid; BORDER-LEFT: #538ba2 1px solid; PADDING-BOTTOM: 0px; LINE-HEIGHT: 0; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; BACKGROUND: #d7e6f7; FONT-SIZE: 0px; OVERFLOW: hidden; BORDER-TOP: #538ba2 1px solid; BORDER-RIGHT: #538ba2 1px solid; PADDING-TOP: 0px
}
.z-window-move-ghost DL {
	BORDER-BOTTOM: #538ba2 1px solid; BORDER-LEFT: #538ba2 1px solid; PADDING-BOTTOM: 0px; LINE-HEIGHT: 0; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; DISPLAY: block; BACKGROUND: #d7e6f7; FONT-SIZE: 0px; OVERFLOW: hidden; BORDER-TOP: #538ba2 1px solid; BORDER-RIGHT: #538ba2 1px solid; PADDING-TOP: 0px
}
.z-window-embedded {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; ZOOM: 1; OVERFLOW: hidden; PADDING-TOP: 0px
}
.z-window-modal {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; ZOOM: 1; OVERFLOW: hidden; PADDING-TOP: 0px
}
.z-window-overlapped {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; ZOOM: 1; OVERFLOW: hidden; PADDING-TOP: 0px
}
.z-window-popup {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; ZOOM: 1; OVERFLOW: hidden; PADDING-TOP: 0px
}
.z-window-highlighted {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; ZOOM: 1; OVERFLOW: hidden; PADDING-TOP: 0px
}
.z-window-embedded-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-window-modal-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-window-highlighted-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-window-overlapped-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-window-popup-tl {
	LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat 0px top; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: 5px
}
.z-window-embedded-tl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-corner.png'))})
}
.z-window-popup-tl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-pop-corner.png'))})
}
.z-window-embedded-tr {
	POSITION: relative; LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat right -10px; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: -5px
}
.z-window-modal-tr {
	POSITION: relative; LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat right -10px; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: -5px
}
.z-window-highlighted-tr {
	POSITION: relative; LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat right -10px; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: -5px
}
.z-window-overlapped-tr {
	POSITION: relative; LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat right -10px; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: -5px
}
.z-window-popup-tr {
	POSITION: relative; LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat right -10px; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: -5px
}
.z-window-embedded-tr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-corner.png'))})
}
.z-window-popup-tr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-pop-corner.png'))})
}
.z-window-embedded-hl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hl.png'))}) no-repeat 0px 0px
}
.z-window-modal-hl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hl.png'))}) no-repeat 0px 0px
}
.z-window-highlighted-hl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hl.png'))}) no-repeat 0px 0px
}
.z-window-overlapped-hl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hl.png'))}) no-repeat 0px 0px
}
.z-window-popup-hl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hl.png'))}) no-repeat 0px 0px
}
.z-window-embedded-hl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-hl.png'))})
}
.z-window-popup-hl {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-pop-hl.png'))})
}
.z-window-embedded-hr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hr.png'))}) no-repeat right 0px
}
.z-window-modal-hr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hr.png'))}) no-repeat right 0px
}
.z-window-highlighted-hr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hr.png'))}) no-repeat right 0px
}
.z-window-overlapped-hr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hr.png'))}) no-repeat right 0px
}
.z-window-popup-hr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hr.png'))}) no-repeat right 0px
}
.z-window-embedded-hr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-hr.png'))})
}
.z-window-embedded-hr-noborder {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-hr.png'))})
}
.z-window-popup-hr {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-pop-hr.png'))})
}
.z-window-embedded-hm {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hm.png'))}) repeat-x 0px 0px; OVERFLOW: hidden
}
.z-window-modal-hm {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hm.png'))}) repeat-x 0px 0px; OVERFLOW: hidden
}
.z-window-highlighted-hm {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hm.png'))}) repeat-x 0px 0px; OVERFLOW: hidden
}
.z-window-overlapped-hm {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hm.png'))}) repeat-x 0px 0px; OVERFLOW: hidden
}
.z-window-popup-hm {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-hm.png'))}) repeat-x 0px 0px; OVERFLOW: hidden
}
.z-window-embedded-hm {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-hm.png'))})
}
.z-window-popup-hm {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-pop-hm.png'))})
}
.z-window-modal-header {
	PADDING-BOTTOM: 4px; ZOOM: 1; FONT-FAMILY: Tahoma; COLOR: #222; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: normal
}
.z-window-popup-header {
	PADDING-BOTTOM: 4px; ZOOM: 1; FONT-FAMILY: Tahoma; COLOR: #222; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: normal
}
.z-window-highlighted-header {
	PADDING-BOTTOM: 4px; ZOOM: 1; FONT-FAMILY: Tahoma; COLOR: #222; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: normal
}
.z-window-overlapped-header {
	PADDING-BOTTOM: 4px; ZOOM: 1; FONT-FAMILY: Tahoma; COLOR: #222; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: normal
}
.z-window-embedded-header {
	PADDING-BOTTOM: 4px; ZOOM: 1; FONT-FAMILY: Tahoma; COLOR: #222; FONT-SIZE: 11px; OVERFLOW: hidden; CURSOR: default; FONT-WEIGHT: normal
}
.z-window-modal-header {
	COLOR: #fff
}
.z-window-popup-header {
	COLOR: #fff
}
.z-window-highlighted-header {
	COLOR: #fff
}
.z-window-overlapped-header {
	COLOR: #fff
}
.z-window-embedded-header A {
	COLOR: #222
}
.z-window-embedded-header A:visited {
	COLOR: #222
}
.z-window-embedded-header A:hover {
	COLOR: #222
}
.z-window-modal-header-move {
	CURSOR: move
}
.z-window-highlighted-header-move {
	CURSOR: move
}
.z-window-overlapped-header-move {
	CURSOR: move
}
.z-window-popup-header-move {
	CURSOR: move
}
.z-window-modal-header A {
	COLOR: #fff
}
.z-window-modal-header A:visited {
	COLOR: #fff
}
.z-window-modal-header A:hover {
	COLOR: #fff
}
.z-window-modal-header .z-caption A {
	COLOR: #fff
}
.z-window-modal-header .z-caption A:visited {
	COLOR: #fff
}
.z-window-modal-header .z-caption A:hover {
	COLOR: #fff
}
.z-window-popup-header A {
	COLOR: #fff
}
.z-window-popup-header A:visited {
	COLOR: #fff
}
.z-window-popup-header A:hover {
	COLOR: #fff
}
.z-window-popup-header .z-caption A {
	COLOR: #fff
}
.z-window-popup-header .z-caption A:visited {
	COLOR: #fff
}
.z-window-popup-header .z-caption A:hover {
	COLOR: #fff
}
.z-window-highlighted-header A {
	COLOR: #fff
}
.z-window-highlighted-header A:visited {
	COLOR: #fff
}
.z-window-highlighted-header A:hover {
	COLOR: #fff
}
.z-window-highlighted-header .z-caption A {
	COLOR: #fff
}
.z-window-highlighted-header .z-caption A:visited {
	COLOR: #fff
}
.z-window-highlighted-header .z-caption A:hover {
	COLOR: #fff
}
.z-window-overlapped-header A {
	COLOR: #fff
}
.z-window-overlapped-header A:visited {
	COLOR: #fff
}
.z-window-overlapped-header A:hover {
	COLOR: #fff
}
.z-window-overlapped-header .z-caption A {
	COLOR: #fff
}
.z-window-overlapped-header .z-caption A:visited {
	COLOR: #fff
}
.z-window-overlapped-header .z-caption A:hover {
	COLOR: #fff
}
.z-window-embedded-cnt {
	BORDER-BOTTOM: #538ba2 1px solid; BORDER-LEFT: #538ba2 1px solid; PADDING-BOTTOM: 3px; MARGIN: 0px; PADDING-LEFT: 3px; PADDING-RIGHT: 3px; BORDER-TOP: #538ba2 1px solid; BORDER-RIGHT: #538ba2 1px solid; PADDING-TOP: 3px
}
.z-window-embedded-cnt {
	ZOOM: 1; OVERFLOW: hidden
}
.z-window-embedded-body {
	ZOOM: 1; OVERFLOW: hidden
}
.z-window-overlapped-body {
	ZOOM: 1; OVERFLOW: hidden
}
.z-window-popup-body {
	ZOOM: 1; OVERFLOW: hidden
}
.z-window-highlighted-body {
	ZOOM: 1; OVERFLOW: hidden
}
.z-window-modal-body {
	ZOOM: 1; OVERFLOW: hidden
}
.z-window-overlapped-cnt {
	PADDING-BOTTOM: 4px; MARGIN: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; ZOOM: 1; BACKGROUND: white; OVERFLOW: hidden; PADDING-TOP: 4px
}
.z-window-popup-cnt {
	PADDING-BOTTOM: 4px; MARGIN: 0px; PADDING-LEFT: 4px; PADDING-RIGHT: 4px; ZOOM: 1; BACKGROUND: white; OVERFLOW: hidden; PADDING-TOP: 4px
}
.z-window-popup-cnt {
	BORDER-BOTTOM: #2c70a9 1px solid; BORDER-LEFT: #2c70a9 1px solid; PADDING-BOTTOM: 2px; MARGIN: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; BORDER-TOP: #2c70a9 1px solid; BORDER-RIGHT: #2c70a9 1px solid; PADDING-TOP: 2px
}
.z-window-modal-cnt {
	PADDING-BOTTOM: 2px; MARGIN: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; ZOOM: 1; BACKGROUND: white; OVERFLOW: hidden; PADDING-TOP: 2px
}
.z-window-highlighted-cnt {
	PADDING-BOTTOM: 2px; MARGIN: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; ZOOM: 1; BACKGROUND: white; OVERFLOW: hidden; PADDING-TOP: 2px
}
.z-window-modal-cnt-noborder {
	PADDING-BOTTOM: 2px; MARGIN: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; ZOOM: 1; BACKGROUND: white; OVERFLOW: hidden; PADDING-TOP: 2px
}
.z-window-highlighted-cnt-noborder {
	PADDING-BOTTOM: 2px; MARGIN: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; ZOOM: 1; BACKGROUND: white; OVERFLOW: hidden; PADDING-TOP: 2px
}
.z-window-overlapped-cnt-noborder {
	PADDING-BOTTOM: 2px; MARGIN: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; ZOOM: 1; BACKGROUND: white; OVERFLOW: hidden; PADDING-TOP: 2px
}
.z-window-modal-cnt-noborder {
	BORDER-RIGHT-WIDTH: 0px; ZOOM: 1; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px
}
.z-window-highlighted-cnt-noborder {
	BORDER-RIGHT-WIDTH: 0px; ZOOM: 1; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px
}
.z-window-embedded-cnt-noborder {
	BORDER-RIGHT-WIDTH: 0px; ZOOM: 1; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px
}
.z-window-overlapped-cnt-noborder {
	BORDER-RIGHT-WIDTH: 0px; ZOOM: 1; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px
}
.z-window-popup-cnt-noborder {
	BORDER-RIGHT-WIDTH: 0px; ZOOM: 1; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px
}
.z-window-popup-cnt-noborder {
	PADDING-BOTTOM: 1px; MARGIN: 0px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; BACKGROUND: white; PADDING-TOP: 1px
}
.z-window-modal-cl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-clr.png'))}) repeat-y 0px 0px
}
.z-window-highlighted-cl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-clr.png'))}) repeat-y 0px 0px
}
.z-window-overlapped-cl {
	PADDING-LEFT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-clr.png'))}) repeat-y 0px 0px
}
.z-window-modal-cr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-clr.png'))}) repeat-y right 0px
}
.z-window-highlighted-cr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-clr.png'))}) repeat-y right 0px
}
.z-window-overlapped-cr {
	PADDING-RIGHT: 6px; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-clr.png'))}) repeat-y right 0px
}
.z-window-modal-cm {
	BORDER-BOTTOM: #0b5ca0 1px solid; BORDER-LEFT: #0b5ca0 1px solid; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: #5eabdb; BORDER-TOP: #0b5ca0 1px solid; BORDER-RIGHT: #0b5ca0 1px solid; PADDING-TOP: 0px
}
.z-window-highlighted-cm {
	BORDER-BOTTOM: #0b5ca0 1px solid; BORDER-LEFT: #0b5ca0 1px solid; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: #5eabdb; BORDER-TOP: #0b5ca0 1px solid; BORDER-RIGHT: #0b5ca0 1px solid; PADDING-TOP: 0px
}
.z-window-overlapped-cm {
	BORDER-BOTTOM: #0b5ca0 1px solid; BORDER-LEFT: #0b5ca0 1px solid; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND: #5eabdb; BORDER-TOP: #0b5ca0 1px solid; BORDER-RIGHT: #0b5ca0 1px solid; PADDING-TOP: 0px
}
.z-window-modal-bl {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat 0px -5px; HEIGHT: 5px; MARGIN-RIGHT: 5px
}
.z-window-highlighted-bl {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat 0px -5px; HEIGHT: 5px; MARGIN-RIGHT: 5px
}
.z-window-overlapped-bl {
	ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat 0px -5px; HEIGHT: 5px; MARGIN-RIGHT: 5px
}
.z-window-modal-br {
	POSITION: relative; LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat right bottom; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: -5px
}
.z-window-highlighted-br {
	POSITION: relative; LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat right bottom; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: -5px
}
.z-window-overlapped-br {
	POSITION: relative; LINE-HEIGHT: 0; ZOOM: 1; BACKGROUND: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/wnd-ol-corner.png'))}) no-repeat right bottom; HEIGHT: 5px; FONT-SIZE: 0px; MARGIN-RIGHT: -5px
}
.z-window-embedded-icon {
	WIDTH: 16px; BACKGROUND: no-repeat 0px 0px; FLOAT: right; HEIGHT: 16px; MARGIN-LEFT: 2px; OVERFLOW: hidden; CURSOR: pointer
}
.z-window-popup-icon {
	WIDTH: 16px; BACKGROUND: no-repeat 0px 0px; FLOAT: right; HEIGHT: 16px; MARGIN-LEFT: 2px; OVERFLOW: hidden; CURSOR: pointer
}
.z-window-modal-icon {
	WIDTH: 16px; BACKGROUND: no-repeat 0px 0px; FLOAT: right; HEIGHT: 16px; MARGIN-LEFT: 2px; OVERFLOW: hidden; CURSOR: pointer
}
.z-window-overlapped-icon {
	WIDTH: 16px; BACKGROUND: no-repeat 0px 0px; FLOAT: right; HEIGHT: 16px; MARGIN-LEFT: 2px; OVERFLOW: hidden; CURSOR: pointer
}
.z-window-highlighted-icon {
	WIDTH: 16px; BACKGROUND: no-repeat 0px 0px; FLOAT: right; HEIGHT: 16px; MARGIN-LEFT: 2px; OVERFLOW: hidden; CURSOR: pointer
}
.z-window-embedded-icon {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/ebd-btn.gif'))})
}
.z-window-modal-icon {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/ol-btn.gif'))})
}
.z-window-highlighted-icon {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/ol-btn.gif'))})
}
.z-window-overlapped-icon {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/ol-btn.gif'))})
}
.z-window-popup-icon {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/wnd/pop-btn.gif'))})
}
.z-window-embedded-min {
	BACKGROUND-POSITION: 0px 0px
}
.z-window-modal-min {
	BACKGROUND-POSITION: 0px 0px
}
.z-window-overlapped-min {
	BACKGROUND-POSITION: 0px 0px
}
.z-window-popup-min {
	BACKGROUND-POSITION: 0px 0px
}
.z-window-highlighted-min {
	BACKGROUND-POSITION: 0px 0px
}
.z-window-embedded-min-over {
	BACKGROUND-POSITION: -16px 0px
}
.z-window-modal-min-over {
	BACKGROUND-POSITION: -16px 0px
}
.z-window-overlapped-min-over {
	BACKGROUND-POSITION: -16px 0px
}
.z-window-popup-min-over {
	BACKGROUND-POSITION: -16px 0px
}
.z-window-highlighted-min-over {
	BACKGROUND-POSITION: -16px 0px
}
.z-window-embedded-max {
	BACKGROUND-POSITION: 0px -16px
}
.z-window-modal-max {
	BACKGROUND-POSITION: 0px -16px
}
.z-window-overlapped-max {
	BACKGROUND-POSITION: 0px -16px
}
.z-window-popup-max {
	BACKGROUND-POSITION: 0px -16px
}
.z-window-highlighted-max {
	BACKGROUND-POSITION: 0px -16px
}
.z-window-embedded-max-over {
	BACKGROUND-POSITION: -16px -16px
}
.z-window-modal-max-over {
	BACKGROUND-POSITION: -16px -16px
}
.z-window-overlapped-max-over {
	BACKGROUND-POSITION: -16px -16px
}
.z-window-popup-max-over {
	BACKGROUND-POSITION: -16px -16px
}
.z-window-highlighted-max-over {
	BACKGROUND-POSITION: -16px -16px
}
.z-window-embedded-maxd {
	BACKGROUND-POSITION: 0px -32px
}
.z-window-modal-maxd {
	BACKGROUND-POSITION: 0px -32px
}
.z-window-overlapped-maxd {
	BACKGROUND-POSITION: 0px -32px
}
.z-window-popup-maxd {
	BACKGROUND-POSITION: 0px -32px
}
.z-window-highlighted-maxd {
	BACKGROUND-POSITION: 0px -32px
}
.z-window-embedded-maxd-over {
	BACKGROUND-POSITION: -16px -32px
}
.z-window-modal-maxd-over {
	BACKGROUND-POSITION: -16px -32px
}
.z-window-overlapped-maxd-over {
	BACKGROUND-POSITION: -16px -32px
}
.z-window-popup-maxd-over {
	BACKGROUND-POSITION: -16px -32px
}
.z-window-highlighted-maxd-over {
	BACKGROUND-POSITION: -16px -32px
}
.z-window-embedded-close {
	BACKGROUND-POSITION: 0px -48px
}
.z-window-modal-close {
	BACKGROUND-POSITION: 0px -48px
}
.z-window-overlapped-close {
	BACKGROUND-POSITION: 0px -48px
}
.z-window-popup-close {
	BACKGROUND-POSITION: 0px -48px
}
.z-window-highlighted-close {
	BACKGROUND-POSITION: 0px -48px
}
.z-window-embedded-close-over {
	BACKGROUND-POSITION: -16px -48px
}
.z-window-modal-close-over {
	BACKGROUND-POSITION: -16px -48px
}
.z-window-overlapped-close-over {
	BACKGROUND-POSITION: -16px -48px
}
.z-window-popup-close-over {
	BACKGROUND-POSITION: -16px -48px
}
.z-window-highlighted-close-over {
	BACKGROUND-POSITION: -16px -48px
}
.z-messagebox-btn {
	MIN-WIDTH: 45pt; WIDTH: 100%
}
.z-msgbox {
	BORDER-RIGHT-WIDTH: 0px; WIDTH: 32px; DISPLAY: inline-block; BACKGROUND-REPEAT: no-repeat; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 32px; VERTICAL-ALIGN: top; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
}
.z-msgbox-question {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/msgbox/question-btn.png'))})
}
.z-msgbox-exclamation {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/msgbox/warning-btn.png'))})
}
.z-msgbox-information {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/msgbox/info-btn.png'))})
}
.z-msgbox-error {
	BACKGROUND-IMAGE: url(${c:encodeURL(c:cat3('~./',project,'/zul/img/msgbox/stop-btn.png'))})
}
.z-messagebox-btn {
	WIDTH: 47pt; TEXT-OVERFLOW: ellipsis
}
.z-messagebox-btn TABLE {
	WIDTH: 100%
}
