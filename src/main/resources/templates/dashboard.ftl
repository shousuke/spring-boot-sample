<#include "base.ftl" />

<#macro page_title>Dashboard</#macro>

<#macro page_sidebar>
  <#assign pageId = pageId!'statsSettlements'>
  
  <ul class="nav nav-sidebar">
    <li class="<#if pageId == 'statsSettlements'>active</#if>">
      <a href="<@spring.url '/dashboard/stats/settlements'/>">结算统计</a>
    </li>
    <li class="<#if pageId == 'statsSubsidies'>active</#if>">
      <a href="<@spring.url '/dashboard/stats/subsidies'/>">补贴统计</a>
    </li>
    <li class="<#if pageId == 'statsCpos'>active</#if>">
      <a href="<@spring.url '/dashboard/stats/cpos'/>">CPOS统计</a>
    </li>
  </ul>
</#macro>

<#macro page_body>
  <h2 class="sub-header">${subTitle!}</h2>
  <div class="table-responsive">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Header</th>
          <th>Header</th>
          <th>Header</th>
          <th>Header</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1,001</td>
          <td>Lorem</td>
          <td>ipsum</td>
          <td>dolor</td>
          <td>sit</td>
        </tr>
        <tr>
          <td>1,002</td>
          <td>amet</td>
          <td>consectetur</td>
          <td>adipiscing</td>
          <td>elit</td>
        </tr>
      </tbody>
    </table>
  </div>
</#macro>

<@display_page />