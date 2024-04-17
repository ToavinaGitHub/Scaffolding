import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  Tarif: React.FC = () => {
  const url = 'http://localhost:8080/';

  const [loading, setLoading] = useState(true);

  const [count, setCount] = useState(1);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [show2, setShow2] = useState(false);

  const handleClose2 = () => setShow2(false);
  const handleShow2 = () => setShow2(true);

  const [error, setError] = useState(null);
  const [selectedItem, setSelectedItem] =  useState<any>(null);
  const handleSelectItem = (itemKey : any) => {
    handleShow2();
    const itemDetails = tarif.find((item: { idTarif: any; }) => item.idTarif === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [tarif, setTarif] = useState([]);
	
	

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 3; 
  const totalPageCount = Math.ceil(count / itemsPerPage);

  //////////// SAVE
  const handleSaveSubmit = async (event : any) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data :any = {};

    for (let [key, value] of formData.entries()) {
      if (form.elements[key].tagName === 'SELECT') {
        data[key] = { id: value };
      } else {
        data[key] = value;
      }
    }

    try {
      const response = await fetch(url + 'tarif', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      handleClose();
      // If you want to reload the page after success
      window.location.reload();
    } catch (error : any) {
      console.log('Error:', error);
    }
  };

  //////////// UPDATE
  const handleUpdateSubmit = async (event : any) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data :any = {};
    for (let [key, value] of formData.entries()) {
      if (form.elements[key].tagName === 'SELECT') {
        data[key] = { id : value };
      } else {
        data[key] = value;
      }
    }
    try {
      const response = await fetch(url + 'tarif', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      handleClose2();
      // If you want to reload the page after success
      window.location.reload();
    } catch (error) {
      console.error('Error:', error);
    }
  };

  //////////// DELETE
  const handleDeleteClick = async (item : any) => {
    try {
      console.log(item);
      const response = await fetch(url + 'tarif', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(item)
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      // If you want to reload the page after success
      window.location.reload();
    } catch (error) {
      console.error('Error:', error);
    }
  };

    	const handleInputHeureFinChange = (event :any) => {
		setSelectedItem({ ...selectedItem, heureFin: event.target.value });
	};
	
	const handleInputIdTarifChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idTarif: event.target.value });
	};
	
	const handleInputDescriptionChange = (event :any) => {
		setSelectedItem({ ...selectedItem, description: event.target.value });
	};
	
	const handleInputMontantChange = (event :any) => {
		setSelectedItem({ ...selectedItem, montant: event.target.value });
	};
	
	const handleInputHeureDebutChange = (event :any) => {
		setSelectedItem({ ...selectedItem, heureDebut: event.target.value });
	};
	
	const handleInputAgeChange = (event :any) => {
		setSelectedItem({ ...selectedItem, age: event.target.value });
	};
	
	

    	useEffect(() => {
		const getTarif = async () => {
			try {
				const response = await fetch(url + 'tarif?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setTarif(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getTarif();
	}, [currentPage]);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add Tarif
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Heure Fin</label> 
	 	<input className="form-control" type="number" name="heureFin" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Description</label> 
	 	<input className="form-control" type="text" name="description" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Montant</label> 
	 	<input className="form-control" type="number" name="montant" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Heure Debut</label> 
	 	<input className="form-control" type="number" name="heureDebut" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Age</label> 
	 	<input className="form-control" type="text" name="age" />
	</div>
	
                <IonButton type="submit" >
                  Save Changes
                </IonButton>
              </form>
            </IonModal>
          </div>
        </div>
        <div className="row">
          <table className="table">
            <thead id="table-head">
              <tr>
                			<th> Heure Fin </th>
			<th> Id Tarif </th>
			<th> Description </th>
			<th> Montant </th>
			<th> Heure Debut </th>
			<th> Age </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {tarif.map((item :any) => (
                <tr key={item.idTarif}>
                  		<td>{item.heureFin}</td>
		<td>{item.idTarif}</td>
		<td>{item.description}</td>
		<td>{item.montant}</td>
		<td>{item.heureDebut}</td>
		<td>{item.age}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.idTarif)}>
                      Update
                    </IonButton>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <IonModal isOpen={show2} onDidDismiss={handleClose2}>
            <form action="" method="" id="update" onSubmit={handleUpdateSubmit}>
              	<div className="mb-3"> 
	 	<label className="form-label">Heure Fin</label> 
	 	<input className="form-control" type="number" name="heureFin" onChange={handleInputHeureFinChange} value={selectedItem ? selectedItem.heureFin:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idTarif" onChange={handleInputIdTarifChange} value={selectedItem ? selectedItem.idTarif:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Description</label> 
	 	<input className="form-control" type="text" name="description" onChange={handleInputDescriptionChange} value={selectedItem ? selectedItem.description:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Montant</label> 
	 	<input className="form-control" type="number" name="montant" onChange={handleInputMontantChange} value={selectedItem ? selectedItem.montant:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Heure Debut</label> 
	 	<input className="form-control" type="number" name="heureDebut" onChange={handleInputHeureDebutChange} value={selectedItem ? selectedItem.heureDebut:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Age</label> 
	 	<input className="form-control" type="text" name="age" onChange={handleInputAgeChange} value={selectedItem ? selectedItem.age:''} />
	</div>
	
              <IonButton type="submit">
                Save Changes
              </IonButton>
            </form>
          </IonModal>
        </div>
        {/* Pagination */ }
        <nav aria-label="Page navigation">
        <ul className="pagination justify-content-center">
          <li className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(prevPage => prevPage - 1)} disabled={currentPage === 1}>Previous</button>
          </li>
          {[...Array(totalPageCount)].map((_, index) => (
            <li key={index} className={`page-item ${currentPage === index + 1 ? 'active' : ''}`}>
              <button className="page-link" onClick={() => setCurrentPage(index + 1)}>{index + 1}</button>
            </li>
          ))}
          <li className={`page-item ${currentPage === totalPageCount+1 ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(prevPage => prevPage + 1)} disabled={currentPage === totalPageCount+1}>Next</button>
          </li>
        </ul>
      </nav>
      </div>
    </IonPage>
  )
}

export default  Tarif;
