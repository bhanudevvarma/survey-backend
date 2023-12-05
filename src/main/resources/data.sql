-- Insert sample survey data
INSERT INTO survey (title) VALUES
  ('Customer Satisfaction Survey'),
  ('Employee Feedback Survey');

-- Insert sample question data
INSERT INTO question (text, type, survey_id) VALUES
  ('How satisfied are you with our service?', 'SLIDER', 1),
  ('What can we improve in our products?', 'FILL_IN_THE_BLANKS', 1),
  ('Rate your overall experience', 'MULTIPLE_CHOICE', 1),
  ('How likely are you to recommend us to a friend?', 'SINGLE_CHOICE', 1),

  ('How satisfied are you with your work environment?', 'SLIDER', 2),
  ('Do you feel heard in the workplace?', 'SINGLE_CHOICE', 2),
  ('How would you rate the team collaboration?', 'MULTIPLE_CHOICE', 2),
  ('Any suggestions for improving team communication?', 'FILL_IN_THE_BLANKS', 2);
