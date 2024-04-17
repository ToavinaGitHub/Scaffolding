import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  DataCsv: React.FC = () => {
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
    const itemDetails = data_csv.find((item: { id: any; }) => item.id === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [data_csv, setData_csv] = useState([]);
	
	

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
      const response = await fetch(url + 'data_csv', {
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
      const response = await fetch(url + 'data_csv', {
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
      const response = await fetch(url + 'data_csv', {
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

    	const handleInputHeureChange = (event :any) => {
		setSelectedItem({ ...selectedItem, heure: event.target.value });
	};
	
	const handleInputCategorieChange = (event :any) => {
		setSelectedItem({ ...selectedItem, categorie: event.target.value });
	};
	
	const handleInputNumSeanceChange = (event :any) => {
		setSelectedItem({ ...selectedItem, numSeance: event.target.value });
	};
	
	const handleInputSalleChange = (event :any) => {
		setSelectedItem({ ...selectedItem, salle: event.target.value });
	};
	
	const handleInputIdChange = (event :any) => {
		setSelectedItem({ ...selectedItem, id: event.target.value });
	};
	
	const handleInputFilmChange = (event :any) => {
		setSelectedItem({ ...selectedItem, film: event.target.value });
	};
	
	const handleInputDatyChange = (event :any) => {
		setSelectedItem({ ...selectedItem, daty: event.target.value });
	};
	
	

    	useEffect(() => {
		const getData_csv = async () => {
			try {
				const response = await fetch(url + 'data_csv?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setData_csv(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getData_csv();
	}, [currentPage]);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add DataCsv
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Heure</label> 
	 	<input className="form-control" type="time" name="heure" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Categorie</label> 
	 	<input className="form-control" type="text" name="categorie" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Num Seance</label> 
	 	<input className="form-control" type="text" name="numSeance" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Salle</label> 
	 	<input className="form-control" type="text" name="salle" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Film</label> 
	 	<input className="form-control" type="text" name="film" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Daty</label> 
	 	<input className="form-control" type="date-time local" name="daty" />
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
                			<th> Heure </th>
			<th> Categorie </th>
			<th> Num Seance </th>
			<th> Salle </th>
			<th> Id </th>
			<th> Film </th>
			<th> Daty </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {data_csv.map((item :any) => (
                <tr key={item.id}>
                  		<td>{item.heure}</td>
		<td>{item.categorie}</td>
		<td>{item.numSeance}</td>
		<td>{item.salle}</td>
		<td>{item.id}</td>
		<td>{item.film}</td>
		<td>{item.daty}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.id)}>
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
	 	<label className="form-label">Heure</label> 
	 	<input className="form-control" type="time" name="heure" onChange={handleInputHeureChange} value={selectedItem ? selectedItem.heure:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Categorie</label> 
	 	<input className="form-control" type="text" name="categorie" onChange={handleInputCategorieChange} value={selectedItem ? selectedItem.categorie:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Num Seance</label> 
	 	<input className="form-control" type="text" name="numSeance" onChange={handleInputNumSeanceChange} value={selectedItem ? selectedItem.numSeance:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Salle</label> 
	 	<input className="form-control" type="text" name="salle" onChange={handleInputSalleChange} value={selectedItem ? selectedItem.salle:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="id" onChange={handleInputIdChange} value={selectedItem ? selectedItem.id:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Film</label> 
	 	<input className="form-control" type="text" name="film" onChange={handleInputFilmChange} value={selectedItem ? selectedItem.film:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Daty</label> 
	 	<input className="form-control" type="date-time local" name="daty" onChange={handleInputDatyChange} value={selectedItem ? selectedItem.daty:''} />
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

export default  DataCsv;
