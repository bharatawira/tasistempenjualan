
    <div class="sidebar" data-color="orange">
      <!--
        Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red | yellow"
    -->
      <div class="logo">
        <a href="" class="simple-text logo-mini">
          F
        </a>
        <a href="" class="simple-text logo-normal">
          FISHPICK STORE
        </a>
      </div>
      <div class="sidebar-wrapper" id="sidebar-wrapper">
        <ul class="nav">
          <li>
            <a href="./">
              <i class="now-ui-icons design_app"></i>
              <p>Dashboard</p>
            </a>
          </li>
          
          <li>
            <a href="<?= base_url('dashboard/listlaporan')?>">
              <i class="now-ui-icons location_map-big"></i>
              <p>List Laporan</p>
            </a>
          </li>
          
         
          <li>
              <a href="<?= base_url('dashboard/listkaryawan')?>"> 
              <i class="now-ui-icons users_single-02"></i>
              <p>List Karyawan</p>
            </a>
            </li>
      
          
          <li>
            <a href="<?= base_url('dashboard/listikancupang')?>">
              <i class="now-ui-icons education_paper"></i>
              <p>List Ikan Cupang</p>
            </a>
          </li>
        </ul>
      </div>
    </div>
    <div class="main-panel" id="main-panel">