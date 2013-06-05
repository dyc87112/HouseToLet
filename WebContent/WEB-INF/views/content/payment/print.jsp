<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<br>
<div class="container" style="width: 100%">
	<form:form commandName="payment" class="form-horizontal" action="" method="post">
		<fieldset>
			<legend>账单结算-${payment.room.name }房间</legend>
			<div style="text-align: right;">
				<button class="btn btn-normal btn-primary" onclick="preview();">打印账单</button>
			</div>
			<!--startprint-->
			<h4 style="text-align: center;">房租结算通知单（${payment.type }）</h4>
			<p style="text-align: right;">
			结算日期：<fmt:formatDate value="${payment.startDate }" pattern="yyyy年MM月dd日" />--
					<fmt:formatDate value="${payment.endDate }" pattern="yyyy年MM月dd日" />
			</p>
			<table style="width: 100%;border: 0px;text-align: right;">
				<tr>
					<td>房间号：${payment.room.name } </td>
					<td>住户姓名：${payment.rental.customerName }</td>
					<td>入住日期：<fmt:formatDate value="${payment.rental.startDate }" pattern="yyyy年MM月dd日" /></td>
					<td>月租金：${payment.basePayment }元</td>
				</tr>
			</table>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>项目名称</th>
						<th>上月抄见数</th>
						<th>本月抄见数</th>
						<th>本月使用数</th>
						<th>单价</th>
						<th>金额</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>电费</td>
						<td>${payment.startElect }</td>
						<td>${payment.endElect }</td>
						<td><fmt:formatNumber value="${payment.endElect-payment.startElect }" pattern="#.##"/></td>
						<td>${payment.electPrice}</td>
						<td>${payment.electPay }</td>
						<td></td>
					</tr>
					<tr>
						<td>水费</td>
						<td>${payment.startWater }</td>
						<td>${payment.endWater }</td>
						<td><fmt:formatNumber value="${payment.endWater-payment.startWater }" pattern="#.##"/></td>
						<td>${payment.waterPrice}</td>
						<td>${payment.waterPay }</td>
						<td></td>
					</tr>
					<tr>
						<td>房租</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>${payment.basePayment }</td>
						<td></td>
					</tr>
					<tr>
						<td>电信费</td>
						<td></td>
						<td></td>
						<td></td>
						<td>${payment.user.netPrice }</td>
						<td>${payment.netPay }</td>
						<td></td>
					</tr>
					<tr>
						<c:if test="${payment.type == '退房'}">
							<td>退还押金</td>
						</c:if>
						<c:if test="${payment.type != '退房'}">
							<td>房屋押金</td>
						</c:if>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>${payment.deposit  }</td>
						<td></td>
					</tr>
					<tr>
						<td>其他费用</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>${payment.adjustSum  }</td>
						<td>${payment.adjustInfo  }</td>
					</tr>
					<tr>
						<td>结转零头</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>${payment.adjustPrice  }</td>
						<td></td>
					</tr>
					<tr>
						<td>合计</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>${payment.sumPay  }</td>
						<td></td>
					</tr>
					<tr>
						<td>备注</td>
						<td colspan="6"></td>
					</tr>
				</tbody>
			</table>
			<div style="text-align: right;">
				账单创建日期：<fmt:formatDate value="${payment.createDate }" pattern="yyyy年MM月dd日" />
			</div>
			<!--endprint-->
		</fieldset>
	</form:form>
</div>
<script type="text/javascript">
// 打印
function preview()
{
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint-->";
    eprnstr="<!--endprint-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml + '<br>' + prnhtml;
    SetupPage();
    window.print();
}
// 设置默认的页眉页脚
function SetupPage()
{
	try {
			var RegWsh = new ActiveXObject("WScript.Shell");
			hkey_key = "header";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&w&b页码，&p/&P");
			hkey_key = "footer";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&b&d"); //去掉了&u 不显示当前打印页的网址
			hkey_key = "margin_bottom";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39"); //0.39相当于把页面设置里面的边距设置为10
			hkey_key = "margin_left";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39");
			hkey_key = "margin_right";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39");
			hkey_key = "margin_top";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39");
		} catch (e) {
		}
	}
</script>