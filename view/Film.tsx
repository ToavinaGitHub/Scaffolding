import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  Film: React.FC = () => {
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
    const itemDetails = film.find((item: { idFilm: any; }) => item.idFilm === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [film, setFilm] = useState([]);
	
	const [genreFilm, setGenreFilm] = useState([]);
	
	

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
      const response = await fetch(url + 'film', {
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
      const response = await fetch(url + 'film', {
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
      const response = await fetch(url + 'film', {
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

    	const handleInputSaryChange = (event :any) => {
		setSelectedItem({ ...selectedItem, sary: event.target.value });
	};
	
	const handleInputIdFilmChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idFilm: event.target.value });
	};
	
	const handleInputTitreChange = (event :any) => {
		setSelectedItem({ ...selectedItem, titre: event.target.value });
	};
	
	const handleInputDescriptionChange = (event :any) => {
		setSelectedItem({ ...selectedItem, description: event.target.value });
	};
	
	const handleInputDureeChange = (event :any) => {
		setSelectedItem({ ...selectedItem, duree: event.target.value });
	};
	
	const handleSelectGenreFilmChange = (event : any) => {
		setSelectedItem({ ...selectedItem, genreFilm: event.target.value });
	};
	
	const handleInputEtatChange = (event :any) => {
		setSelectedItem({ ...selectedItem, etat: event.target.value });
	};
	
	

    	useEffect(() => {
		const getFilm = async () => {
			try {
				const response = await fetch(url + 'film?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setFilm(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getFilm();
	}, [currentPage]);
	useEffect(() => {
		const getGenreFilm = async () => {
			try {
				const response = await fetch(url + 'genreFilm');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setGenreFilm(data);
			#count#} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getGenreFilm();
	}, []);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add Film
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Sary</label> 
	 	<input className="form-control" type="text" name="sary" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Titre</label> 
	 	<input className="form-control" type="text" name="titre" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Description</label> 
	 	<input className="form-control" type="text" name="description" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Duree</label> 
	 	<input className="form-control" type="number" name="duree" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idFilm</label> 
	 	<select className="form-control" name="genreFilm" id="select-genreFilm">
			{genreFilm.map((elt :any) => (
				<option value={elt.idGenre}>{elt.libelle}</option>
			))}
			
		</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">Etat</label> 
	 	<input className="form-control" type="number" name="etat" />
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
                			<th> Sary </th>
			<th> Id Film </th>
			<th> Titre </th>
			<th> Description </th>
			<th> Duree </th>
			<th> Id Genre Film </th>
			<th> Etat </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {film.map((item :any) => (
                <tr key={item.idFilm}>
                  		<td>{item.sary}</td>
		<td>{item.idFilm}</td>
		<td>{item.titre}</td>
		<td>{item.description}</td>
		<td>{item.duree}</td>
		<td>{item.genreFilm.libelle}</td>
		<td>{item.etat}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.idFilm)}>
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
	 	<label className="form-label">Sary</label> 
	 	<input className="form-control" type="text" name="sary" onChange={handleInputSaryChange} value={selectedItem ? selectedItem.sary:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idFilm" onChange={handleInputIdFilmChange} value={selectedItem ? selectedItem.idFilm:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Titre</label> 
	 	<input className="form-control" type="text" name="titre" onChange={handleInputTitreChange} value={selectedItem ? selectedItem.titre:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Description</label> 
	 	<input className="form-control" type="text" name="description" onChange={handleInputDescriptionChange} value={selectedItem ? selectedItem.description:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Duree</label> 
	 	<input className="form-control" type="number" name="duree" onChange={handleInputDureeChange} value={selectedItem ? selectedItem.duree:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idFilm</label> 
	 	<select className="form-control" name="genreFilm">
			{genreFilm.map((elt : any) => (
		<option value={elt.idGenre}>{elt.libelle}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">Etat</label> 
	 	<input className="form-control" type="number" name="etat" onChange={handleInputEtatChange} value={selectedItem ? selectedItem.etat:''} />
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

export default  Film;
