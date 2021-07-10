<div class="content">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title" align="center">List Ikan Cupang</h4>
              </div>
              <a href="<?= base_url('dashboard/createcupang/')?>"" class="btn btn-info btn-circle">
                                <i class="fas fa-plus"></i> Tambah
                                </a>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table">
                    <thead class=" text-primary">
                        <th>
                            No
                        </th>
                      <th>
                        Nama
                      </th>
                      <th>
                      Harga
                      </th>
                      <th>
                        Gambar
                      </th>
                      
                      <th>
                          Action
                      </th>
                    </thead>
                    <tbody>

                    <?php
                        $no = 1;
                        foreach($datauser as $data => $user){
                            ?>
                      <tr>
                            <td><?= $no;?></td>
                            <td><?= $user['nama_cupang'];?></td>
                            <td><?= $user['harga_cupang'];?></td>
                            
                            <td><img src="<?=base_url('uploads/'.$user["gambar_cupang"])  ?>" width="200" height="150"></td>
                            <td>
                            <a href="<?= base_url('dashboard/edit_cupang/'.$user['id_cupang'])?>"" class="btn btn-warning btn-circle">
                                <i class="fas fa-pen"></i>
                                </a>
                                <a href="<?= base_url('dashboard/delete_cupang/'.$user['id_cupang'])?>" class="btn btn-danger btn-circle">
                                <i class="fas fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                    <?php

                    $no++;
                    }
                    ?>
                        
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Tambah List Ikan Cupang</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form method="POST" enctype="multipart/form-data" action="<?= base_url('dashboard/create_cupang'); ?>">
            <div class="form-group">
                <label for="exampleInputEmail1">Nama</label>
                <input type="text" class="form-control" name="nama_cupang" required>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Harga</label>
                <input type="text" class="form-control" name="harga_cupang" required>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">URL gambar Cupang</label>
                <input type="text" class="form-control" name="gambar_cupang" required>
            </div>
            
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-success">Simpan</button>
        </form>
      </div>
    </div>
  </div>
</div>