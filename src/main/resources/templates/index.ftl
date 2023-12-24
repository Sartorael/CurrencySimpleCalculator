<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    body {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      font-family: 'Courier New', monospace;
      background-image: url("/background.jpg");
      color: #fff;
    }
    form {
      width: 300px;
      padding: 20px;
      border: 2px solid #fff;
      border-radius: 10px;
      background-color: rgba(0, 0, 0, 0.7);
      backdrop-filter: blur(10px);
    }
    h1, h2 {
      text-align: center;
      margin-bottom: 20px;
      text-transform: uppercase;
      color: lime;
    }
    .readonly-field {
      border: 1px solid #ccc;
      background-color: #f8f8f8;
      cursor: text;
      padding: 8px;
      color: #000;
    }
  </style>
</head>
<body>
  <form action="/" method="POST">
<h1>Конвертер валют</h1>
<h2>Выберите API и валюты, а затем введите значение:</h2>
<ul>
  <li>
    <label for="api">API:</label>
    <select id="api" name="api">
      <option value="CurrencyApiCom">CurrencyApiCom</option>
    </select>
  </li>
  <li>
    <label for="currency">Валюта:</label>
    <select id="currency" name="currency">
      <option value="USD">USD</option>
      <option value="EUR">EUR</option>
      <option value="RUB">RUB</option>
    </select>
    <input type="number" id="value" name="value" min="0" value="0">
  </li>
  <li>
    <label for="convertTo">Конвертировать в:</label>
    <select id="convertTo" name="convertTo">
      <option value="USD">USD</option>
      <option value="EUR">EUR</option>
      <option value="RUB">RUB</option>
    </select>
    <input type="text" id="result" name="result" value="${result}" readonly class="readonly-field">
  </li>
</ul>
<input type="submit" value="Отправить">
</form>
  <div id="result"></div>
</body>
</html>