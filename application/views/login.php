<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<?= base_url('assets/css/style1.css')?>">
    <link rel="stylesheet" type="text/css" href="fontawesome/css/all.min.css">
    
    <div class="container">
  
  <div class="row" id="pwd-container">
    <div class="col-md-4"></div>
    
    <div class="col-md-4">
      <section class="login-form">
        <form method="post" action="<?= base_url('dashboard/prosesLogin') ?>" role="login">
              <legend>Login</legend>
          <input type="text" name="username" placeholder="Username" class="form-control" required/>
          
          <input type="password" name="password" class="form-control" placeholder="Password" required/>
          
          
          <a href="home.html">
          <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">Login</button></a>
        
        
        </form>
        
</div>