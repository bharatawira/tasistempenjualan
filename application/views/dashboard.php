<div class="content">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title" align="center">Dashboard</h4>
              </div>
              
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
                        Jenis Ikan
                      </th>
                      <th>
                        Nama Pembeli
                      </th>
                      <th>
                        No Telpon Pembeli
                      </th>
                      <th>
                        Harga Jual
                      </th>
                      <th>
                        Date
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
                            <td><?= $user['id_user'];?></td>
                            <td><?=$user['id_cupang'];?></td>
                            <td><?= $user['nama_pembeli'];?></td>
                            <td><?= $user['notelp_pembeli'];?></td>
                            <td><?= $user['harga_penjualan'];?></td>
                            <td><?= $user['date'];?></td>
                            <td>
                            <a href="<?= base_url('dashboard/proses_edit_nota/'.$user['id_penjualan'])?>"" class="btn btn-success btn-circle">
                                <i class="fas fa-check"></i>
                                </a>
                                <a href="<?= base_url('dashboard/delete_nota/'.$user['id_penjualan'])?>" class="btn btn-danger btn-circle">
                                <bold>X</bold>
                                </a>
                            </td>
                            
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
        <h5 class="modal-title" id="exampleModalLabel">Tambah Laporan</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form method="POST" enctype="multipart/form-data" action="<?= base_url('dashboard/create_nota'); ?>">
            <div class="form-group">
                <label for="exampleInputEmail1">Nama</label>
                <input type="text" class="form-control" name="nama" required>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Jenis Ikan</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="jenis_ikan" required>
                    <option selected>pilih...</option>
                    <option value="Halfmoon">Halfmoon</option>
                    <option value="Yellow Koi">Yellow Koi</option>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Harga</label>
                <input type="text" class="form-control" name="harga" required>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Status</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="status" required>
                    <option selected>pilih...</option>
                    <option value="Berhasil">Berhasil</option>
                    <option value="Tidak Berhasil">Tidak Berhasil</option>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Date</label>
                <input type="date" class="form-control" name="date" required>
            </div>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-success">Simpan</button>
        </form>
      </div>
    </div>
  </div>
</div>