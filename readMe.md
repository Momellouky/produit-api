<h1>Product api </h1>

<p>
    A product api responsible of delivring products as ressource to clients. The ressource is represented as json - JavaScript object notation - file. 
    The api makes sure to insert accurate data to the database, validating data sent by the client in the HTTP body request. Also, it implement a logic to ensure the coherence of data after any modification.

</p>
<P>
    The api is designed to respond to the following HTTP methods: 
</P>
<ul>
    <li>GET</li>
    <li>POST</li>
    <li>PUT</li>
    <li>DELETE</li>
</ul>


<h1>Ressource locator </h1>

<p>
    the URI is composed of the base URL, in general it is the domain name of the sevrer, followed by the end point. 
</p>

<p>
    the end point, by convention is: <br> <code>/produits/</code> 
</p>

<!-- CSS Code:  -->
<style>
table.GeneratedTable {
  width: 100%;
  background-color: #ffffff;
  border-collapse: collapse;
  border-width: 2px;
  border-color: #0080FF;
  border-style: solid;
  color: #000;
}

table.GeneratedTable td, table.GeneratedTable th {
  border-width: 2px;
  border-color: #646464;
  border-style: solid;
  padding: 5px;
  color: #000
}

table.GeneratedTable thead {
  background-color: #646464;
}
</style>

<!-- HTML Code -->

<table class="GeneratedTable">
  <thead>
    <tr>
      <th>HTTP METHOD</th>
      <th>END POINT</th>
      <th>RESULT</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>GET</td>
      <td>/produits/</td>
      <td>returns all products in the databse</td>
    </tr>
    <tr>
      <td>GET</td>
      <td>/produits/:id</td>
      <td>returns a specific product if it exists</td>
    </tr>
    <tr>
      <td>POST</td>
      <td>/produits/</td>
      <td>insert the given product in the HTTP request body to the database</td>
    </tr>
    <tr>
      <td>PUT</td>
      <td>/produits/:id</td>
      <td>update a product in the database if it exists</td>
    </tr>
    <tr>
      <td>DELETE</td>
      <td>/produits/:id</td>
      <td>delete a product in the database if it exists</td>
    </tr>
  </tbody>
</table>


